package com.api.auth.application.request

data class SignInRequest(
    val username: String,
    val password: String
)
