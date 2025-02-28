package com.common.global.auth.interceptor

import com.common.global.auth.support.AuthenticationContext
import com.common.global.auth.support.AuthenticationExtractor
import com.common.global.auth.token.TokenProvider
import com.common.global.exceptions.TokenExceptionType
import com.common.global.exceptions.base.CustomException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class LoginValidCheckerInterceptor(
    private val tokenProvider: TokenProvider,
    private val authenticationContext: AuthenticationContext
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val token = AuthenticationExtractor.extract(request)
            .orElseThrow { CustomException(TokenExceptionType.TOKEN_SIGNATURE_INVALID_EXCEPTION) }

        val memberId = tokenProvider.extract(token)
        authenticationContext.setAuthentication(memberId)

        return true
    }
}
