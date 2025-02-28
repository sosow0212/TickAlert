package com.core.auth.domain

interface AuthRepository {

    fun save(auth: Auth): Auth

    fun findByUsername(username: String): Auth?

    fun existsByUsername(username: String): Boolean
}
