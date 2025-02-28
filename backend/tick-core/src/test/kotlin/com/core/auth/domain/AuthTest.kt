package com.core.auth.domain

import com.core.auth.AuthFixture.Companion.인증_생성
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@SuppressWarnings("NonAsciiCharacters")
class AuthTest {

    private lateinit var passwordEncryptor: AuthPasswordEncryptor

    @BeforeEach
    fun setup() {
        passwordEncryptor = AuthPasswordFakeEncryptor()
    }

    @Test
    fun `패스워드가 일치하면 true를 반환한다`() {
        // given
        val auth = 인증_생성()

        // when
        val matches = auth.matches(auth.password, passwordEncryptor)

        // then
        assertThat(matches).isTrue()
    }

    @Test
    fun `암호화해서 Auth를 생성한다`() {
        // given
        val password = "password";

        // when
        val authWithEncrypted = Auth.signUpWithEncryption("username", password, passwordEncryptor)

        // then
        assertThat(authWithEncrypted.password).isEqualTo("encrypted")
    }
}
