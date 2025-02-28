package com.common.global.auth.resolver

import com.common.global.auth.annotation.AuthMember
import com.common.global.auth.support.AuthenticationContext
import com.common.global.exceptions.AuthExceptionType
import com.common.global.exceptions.base.CustomException
import org.springframework.core.MethodParameter
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthMemberArgumentResolver(
    private val authenticationContext: AuthenticationContext
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthMember::class.java) &&
                parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        @Nullable mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        @Nullable binderFactory: WebDataBinderFactory?
    ): Any? {
        return authenticationContext.getPrincipal()
            .takeIf { it != ANONYMOUS_AUTH_ID }
            ?: throw CustomException(AuthExceptionType.AUTH_NOT_FOUND_EXCEPTION)
    }

    companion object {
        private const val ANONYMOUS_AUTH_ID: Long = -1L
    }
}
