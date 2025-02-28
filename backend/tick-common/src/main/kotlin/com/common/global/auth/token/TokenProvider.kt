package com.common.global.auth.token

interface TokenProvider {

    fun create(id: Long): String

    fun extract(token: String): Long
}
