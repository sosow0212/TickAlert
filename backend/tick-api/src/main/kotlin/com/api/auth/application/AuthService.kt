package com.api.auth.application

import com.api.auth.application.request.SignInRequest
import com.api.auth.application.request.SignUpRequest
import com.common.global.auth.token.TokenProvider
import com.common.global.exceptions.AuthExceptionType
import com.common.global.exceptions.AuthExceptionType.PASSWORD_INVALID_EXCEPTION
import com.common.global.exceptions.base.CustomException
import com.core.auth.domain.Auth
import com.core.auth.domain.AuthPasswordEncryptor
import com.core.auth.domain.AuthRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val authRepository: AuthRepository,
    private val authPasswordEncryptor: AuthPasswordEncryptor,
    private val tokenProvider: TokenProvider
) {

    @Transactional
    fun signUp(request: SignUpRequest): String {
        if (authRepository.existsByUsername(request.username)) {
            throw CustomException(AuthExceptionType.USERNAME_ALREADY_EXISTS_EXCEPTION)
        }

        val savedAuth = authRepository.save(
            Auth.signUpWithEncryption(
                username = request.username,
                password = request.password,
                authPasswordEncryptor = authPasswordEncryptor
            )
        )

        return tokenProvider.create(savedAuth.id)
    }

    @Transactional(readOnly = true)
    fun signIn(command: SignInRequest): String {
        val auth = authRepository.findByUsername(command.username)
            ?: throw CustomException(AuthExceptionType.AUTH_NOT_FOUND_EXCEPTION)

        require(auth.matches(command.password, authPasswordEncryptor)) {
            PASSWORD_INVALID_EXCEPTION
        }

        return tokenProvider.create(auth.id)
    }
}
