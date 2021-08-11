package br.com.itau.bff.gateway.feign.config

import feign.Logger
import feign.Request
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.util.concurrent.TimeUnit

open class EmailApiConfig {

    @Value("\${feign.apigateway.connectTimeout}")
    private val connectTimeout: Long = 1000

    @Value("\${feign.apigateway.readTimeout}")
    private val readTimeout: Long = 1000

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun options(): Request.Options {
        return Request.Options(connectTimeout, TimeUnit.MILLISECONDS, readTimeout, TimeUnit.MILLISECONDS, true)
    }

    @Bean("mailApiInterceptor")
    fun mailApiInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        }
    }

}