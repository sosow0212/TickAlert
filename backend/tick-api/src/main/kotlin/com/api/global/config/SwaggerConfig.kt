package com.api.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Value("\${swagger.url}")
    private lateinit var swaggerUrl: String

    @Bean
    fun openAPI(): OpenAPI {
        val securityRequirement = SecurityRequirement().addList(BEARER_FORMAT)
        val components = Components().apply {
            addSecuritySchemes(
                BEARER_FORMAT,
                SecurityScheme()
                    .name(BEARER_FORMAT)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat(BEARER_FORMAT)
                    .description("토큰값을 입력하여 인증을 활성화할 수 있습니다.")
            )
        }

        return OpenAPI()
            .components(components)
            .info(
                Info()
                    .title("Winwin API")
                    .description("Winwin API 문서")
                    .version("1.0")
            )
            .addSecurityItem(securityRequirement)
            .addServersItem(Server().apply { url = swaggerUrl })
    }

    companion object {
        private const val BEARER_FORMAT = "JWT"
    }
}
