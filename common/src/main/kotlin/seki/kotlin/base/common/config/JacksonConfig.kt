package seki.kotlin.base.common.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

/**
 * JacksonMapper 設定
 */
@Configuration
class JacksonConfig {
    @Bean
    @Primary
    fun objectMapper(): ObjectMapper =
        ObjectMapper().apply {
            this.registerModules(JavaTimeModule())
            this.registerKotlinModule()
            this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
            this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
}
