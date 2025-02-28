package com.api.auth.ui

import com.api.auth.application.request.SignInRequest
import com.api.auth.application.request.SignUpRequest
import com.api.helper.IntegrationHelper
import com.core.auth.domain.Auth
import com.core.auth.domain.AuthPasswordEncryptor
import com.core.auth.domain.AuthRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

class AuthControllerAcceptanceTest(
    @Autowired
    private val authRepository: AuthRepository,

    @Autowired
    private val authPasswordEncryptor: AuthPasswordEncryptor
) : IntegrationHelper() {

    @Test
    fun `회원가입을 진행한다`() {
        // given
        val request = SignUpRequest("username", "password")

        // when
        val response = RestAssured.given().log().all()
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .post("/auth/sign-up")
            .then().log().all()
            .extract()

        // then
        assertEquals(response.statusCode(), HttpStatus.CREATED.value())
    }

    @Test
    fun `로그인을 진행한다`() {
        // given
        authRepository.save(
            Auth.signUpWithEncryption(
                username = "username",
                password = "password",
                authPasswordEncryptor = authPasswordEncryptor
            )
        )
        val request = SignInRequest("username", "password")

        // when
        val response = RestAssured.given().log().all()
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .get("/auth/sign-in")
            .then().log().all()
            .extract()

        // then
        assertEquals(response.statusCode(), HttpStatus.OK.value())
    }
}
