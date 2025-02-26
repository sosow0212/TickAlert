package com.api.global

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/health")
    fun health(): String {
        return "up"
    }

    @GetMapping("/exception")
    fun exception() = IllegalArgumentException("예외 발생!")
}
