package com.common.global.auth.interceptor

import com.common.global.auth.support.AuthenticationContext
import com.common.global.auth.support.AuthenticationExtractor
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class ParseMemberIdFromTokenInterceptor(
    private val loginValidCheckerInterceptor: LoginValidCheckerInterceptor,
    private val authenticationContext: AuthenticationContext
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if (AuthenticationExtractor.extract(request).isEmpty) {
            authenticationContext.setAnonymous()
            return true
        }
        return loginValidCheckerInterceptor.preHandle(request, response, handler)
    }
}
