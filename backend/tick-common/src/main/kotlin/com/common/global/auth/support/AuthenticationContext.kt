package com.common.global.auth.support

import com.common.global.exceptions.AuthExceptionType
import com.common.global.exceptions.base.CustomException
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@RequestScope
@Component
class AuthenticationContext(
    private var memberId: Long? = null
) {

    fun setAuthentication(memberId: Long) {
        this.memberId = memberId
    }

    fun getPrincipal(): Long {
        return memberId
            ?: throw CustomException(AuthExceptionType.AUTH_NOT_FOUND_EXCEPTION)
    }

    fun setAnonymous() {
        this.memberId = ANONYMOUS_MEMBER
    }

    companion object {
        private const val ANONYMOUS_MEMBER = -1L
    }
}
