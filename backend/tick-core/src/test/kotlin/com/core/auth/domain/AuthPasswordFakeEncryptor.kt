package com.core.auth.domain

class AuthPasswordFakeEncryptor : AuthPasswordEncryptor {

    override fun encrypt(password: String): String {
        return "encrypted"
    }

    override fun matches(password: String, encodedPassword: String): Boolean {
        return true
    }
}
