package com.common.global.exceptions.base

open class CustomException(
    private val customExceptionType: CustomExceptionType
) : RuntimeException("[${customExceptionType.subject}]: ${customExceptionType.message}") {

    fun getExceptionType(): CustomExceptionType {
        return customExceptionType
    }
}
