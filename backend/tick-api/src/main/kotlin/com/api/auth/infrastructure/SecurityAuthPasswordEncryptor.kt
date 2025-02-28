package com.api.auth.infrastructure

import com.core.auth.domain.AuthPasswordEncryptor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurityAuthPasswordEncryptor(
    private val passwordEncoder: PasswordEncoder
) : AuthPasswordEncryptor {

    override fun encrypt(password: String): String {
        return passwordEncoder.encode(password)
    }

    override fun matches(password: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(password, encodedPassword)
    }
}
