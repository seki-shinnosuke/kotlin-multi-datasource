package seki.kotlin.base.api

import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.annotation.MapperScans
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/**
 * アプリケーション起動用クラス
 */
@MapperScans(
    MapperScan("seki.kotlin.base.common.db.mapper"),
    MapperScan("seki.kotlin.base.api.db.custom.mapper"),
)
@SpringBootApplication(
    scanBasePackages = [
        "seki.kotlin.base.common",
        "seki.kotlin.base.api",
    ],
)
@ConfigurationPropertiesScan(
    basePackages = [
        "seki.kotlin.base.common",
        "seki.kotlin.base.api",
    ],
)
class ApiApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            runApplication<ApiApplication>(*args)
        }
    }
}
