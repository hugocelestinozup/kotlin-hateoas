package br.com.itau.bff.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.support.RetryTemplate
import org.springframework.retry.support.RetryTemplateBuilder

@Configuration
class SpringRetryConfig {

    @Value("\${config.retry.maxAttempts}")
    var maxAttempts: Int = 1

    @Value("\${config.retry.maxDelay}")
    var maxDelay: Long = 1L

    @Bean
    fun retryTemplate() : RetryTemplate {
        return RetryTemplateBuilder().maxAttempts(maxAttempts).fixedBackoff(maxDelay).build()
    }

}