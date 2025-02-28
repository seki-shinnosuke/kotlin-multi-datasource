package seki.kotlin.base.common.config.db

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import seki.kotlin.base.common.enumeration.DataSourceType
import javax.sql.DataSource

@Configuration
class DbConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun primaryDatasourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean(name = [PRIMARY_DATA_SOURCE])
    @ConfigurationProperties("spring.datasource.hikari")
    fun primaryDatasource(primaryDatasourceProperties: DataSourceProperties): DataSource {
        return primaryDatasourceProperties.initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
    }

    @Bean
    @ConfigurationProperties(prefix = "secondary.datasource")
    fun secondaryDatasourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean(name = [SECONDARY_DATA_SOURCE])
    @ConfigurationProperties("secondary.datasource.hikari")
    fun secondaryDatasource(secondaryDatasourceProperties: DataSourceProperties): DataSource {
        return secondaryDatasourceProperties.initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
    }

    @Bean(name = [ROUTING_DATA_SOURCE])
    fun routingDataSource(
        @Qualifier(PRIMARY_DATA_SOURCE) primaryDatasource: DataSource,
        @Qualifier(SECONDARY_DATA_SOURCE) secondaryDatasource: DataSource,
    ): DataSource {
        val routingDataSource = TransactionRoutingDataSource()
        val dataSourceMap =
            mapOf<Any, Any>(
                DataSourceType.READ_WRITE to primaryDatasource,
                DataSourceType.READ_ONLY to secondaryDatasource,
            )
        routingDataSource.setTargetDataSources(dataSourceMap)
        routingDataSource.setDefaultTargetDataSource(primaryDatasource)
        return routingDataSource
    }

    @Bean
    @Primary
    fun dataSource(
        @Qualifier(ROUTING_DATA_SOURCE) routingDataSource: DataSource,
    ): DataSource {
        return LazyConnectionDataSourceProxy(routingDataSource)
    }

    companion object {
        const val PRIMARY_DATA_SOURCE = "primaryDatasource"
        const val SECONDARY_DATA_SOURCE = "secondaryDatasource"
        const val ROUTING_DATA_SOURCE = "routingDataSource"
    }
}

class TransactionRoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any {
        return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            DataSourceType.READ_ONLY
        } else {
            DataSourceType.READ_WRITE
        }
    }
}
