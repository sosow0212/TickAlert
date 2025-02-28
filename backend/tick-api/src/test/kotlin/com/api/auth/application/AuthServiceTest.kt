package com.api.auth.application

import com.api.auth.AuthDtoFixture.Companion.인증_로그인_커맨드
import com.api.auth.AuthDtoFixture.Companion.인증_생성_커맨드
import com.common.global.auth.token.TokenProvider
import com.common.global.exceptions.AuthExceptionType
import com.common.global.exceptions.base.CustomException
import com.core.auth.AuthFixture.Companion.인증_생성
import com.core.auth.domain.AuthPasswordEncryptor
import com.core.auth.domain.AuthRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThatThrownBy

class AuthServiceTest : BehaviorSpec({

    val authRepository: AuthRepository = mockk()
    val authPasswordEncryptor: AuthPasswordEncryptor = mockk()
    val tokenProvider: TokenProvider = mockk()

    val authService = AuthService(authRepository, authPasswordEncryptor, tokenProvider)

    Given("회원 가입을 할 때") {
        When("닉네임이 이미 존재하면") {
            every { authRepository.existsByUsername(any()) } returns true

            Then("예외를 발생시킨다") {
                assertThatThrownBy {
                    authService.signUp(인증_생성_커맨드())
                }.isInstanceOf(CustomException::class.java)
                    .hasMessageContaining(AuthExceptionType.USERNAME_ALREADY_EXISTS_EXCEPTION.message)
            }
        }

        When("닉네임이 존재하지 않으면") {
            every { authRepository.existsByUsername(any()) } returns false
            every { authPasswordEncryptor.encrypt(any()) } returns "password"
            every { authRepository.save(any()) } returns 인증_생성()
            every { tokenProvider.create(any()) } returns "token"

            val command = 인증_생성_커맨드()
            Then("정상 가입이 되고 토큰을 반환한다") {
                val response = authService.signUp(command)
                response shouldBe "token"
            }
        }
    }

    Given("로그인을 진행할 때") {
        When("아이디가 존재하지 않으면") {
            every { authRepository.findByUsername(any()) } returns null

            Then("예외를 발생시킨다") {
                assertThatThrownBy {
                    authService.signIn(인증_로그인_커맨드())
                }.isInstanceOf(CustomException::class.java)
                    .hasMessageContaining(AuthExceptionType.AUTH_NOT_FOUND_EXCEPTION.message)
            }
        }

        When("아이디가 존재하고") {
            val auth = 인증_생성()
            every { authRepository.findByUsername(any()) } returns auth

            Then("패스워드가 일치하지 않으면 예외를 발생시킨다") {
                every { authPasswordEncryptor.matches(any(), any()) } returns false
                assertThatThrownBy {
                    authService.signIn(인증_로그인_커맨드())
                }.isInstanceOf(IllegalArgumentException::class.java)
                    .hasMessageContaining(AuthExceptionType.PASSWORD_INVALID_EXCEPTION.name)
            }

            Then("패스워드가 일치한다면 토큰을 반환한다") {
                every { authPasswordEncryptor.matches(any(), any()) } returns true
                every { tokenProvider.create(any()) } returns "token"

                val response = authService.signIn(인증_로그인_커맨드())
                response shouldBe "token"
            }
        }
    }
})

