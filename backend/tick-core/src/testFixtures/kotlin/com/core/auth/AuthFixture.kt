package com.core.auth

import com.core.auth.domain.Auth

class AuthFixture {

    companion object {
        fun 인증_생성(): Auth {
            return Auth(
                username = "username",
                password = "password"
            )
        }
    }
}
