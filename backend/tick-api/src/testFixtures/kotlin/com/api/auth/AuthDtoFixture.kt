package com.api.auth

import com.api.auth.application.request.SignInRequest
import com.api.auth.application.request.SignUpRequest

class AuthDtoFixture {

    companion object {
        fun 인증_생성_커맨드(): SignUpRequest {
            return SignUpRequest(
                username = "username",
                password = "password"
            )
        }

        fun 인증_로그인_커맨드(): SignInRequest {
            return SignInRequest(
                username = "username",
                password = "password"
            )
        }
    }
}
