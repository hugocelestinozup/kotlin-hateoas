package br.com.itau.bff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@SpringBootApplication
@EnableFeignClients(basePackages = ["br.com.itau.bff"])
class ItauBffApplication

fun main(args: Array<String>) {
    runApplication<ItauBffApplication>(*args)
}