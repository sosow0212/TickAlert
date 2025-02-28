package com.core.auth.infrastructure

import com.core.auth.domain.Auth
import org.springframework.data.jpa.repository.JpaRepository

interface AuthJpaRepository : JpaRepository<Auth, Long> {

    fun existsAuthByUsername(username: String): Boolean

    fun findByUsername(username: String): Auth?
}
