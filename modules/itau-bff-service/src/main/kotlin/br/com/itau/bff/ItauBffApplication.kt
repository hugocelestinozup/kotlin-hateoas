package br.com.itau.bff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ItauBffApplication

fun main(args: Array<String>) {
    runApplication<ItauBffApplication>(*args)
}