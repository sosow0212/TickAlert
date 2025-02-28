package com.core.auth.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Auth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String,
) {

    fun matches(password: String, authPasswordEncryptor: AuthPasswordEncryptor): Boolean {
        return authPasswordEncryptor.matches(password, this.password)
    }

    companion object {
        fun signUpWithEncryption(
            username: String,
            password: String,
            authPasswordEncryptor: AuthPasswordEncryptor
        ) = Auth(username = username, password = authPasswordEncryptor.encrypt(password))
    }
}
