package com.api.helper

import com.common.global.auth.token.TokenProvider
import com.core.auth.domain.Auth
import com.core.auth.domain.AuthPasswordEncryptor
import com.core.auth.domain.AuthRepository
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIntegrationTest : IntegrationHelper() {

    @Autowired
    private lateinit var authRepository: AuthRepository

    @Autowired
    private lateinit var authPasswordEncryptor: AuthPasswordEncryptor

    @Autowired
    private lateinit var tokenProvider: TokenProvider

    protected lateinit var auth: Auth
    protected lateinit var token: String

    @BeforeEach
    override fun init() {
        super.init() //
        setupTestUser()
    }

    private fun setupTestUser() {
        auth = authRepository.save(
            Auth.signUpWithEncryption(
                username = "testuser",
                password = "password",
                authPasswordEncryptor = authPasswordEncryptor
            )
        )
        token = tokenProvider.create(auth.id)
        RestAssured.authentication = RestAssured.oauth2(token)
    }
}
