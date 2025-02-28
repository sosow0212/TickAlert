package com.api.helper

import com.api.auth.application.AuthService
import com.common.global.auth.support.AuthenticationContext
import com.common.global.auth.token.TokenProvider
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.test.context.bean.override.mockito.MockitoBean

open class MockBeanInjection() {

    @MockitoBean
    protected lateinit var jpaMetamodelMappingContext: JpaMetamodelMappingContext

    @MockitoBean
    protected lateinit var tokenProvider: TokenProvider

    @MockitoBean
    protected lateinit var authenticationContext: AuthenticationContext

    @MockitoBean
    protected lateinit var authService: AuthService
}
