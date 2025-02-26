package com.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
class CoreApplication

fun main(args: Array<String>) {
    runApplication<CoreApplication>(*args)
}
