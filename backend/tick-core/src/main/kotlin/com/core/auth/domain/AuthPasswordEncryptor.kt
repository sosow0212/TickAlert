package com.core.auth.domain

interface AuthPasswordEncryptor {

    fun encrypt(password: String): String

    fun matches(password: String, encodedPassword: String): Boolean
}
