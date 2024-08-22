package com.kumaverse.kumabackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KumaBackendApplication

fun main(args: Array<String>) {
    runApplication<KumaBackendApplication>(*args)
}
