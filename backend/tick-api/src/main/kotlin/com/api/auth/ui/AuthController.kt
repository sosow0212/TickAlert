package com.api.auth.ui

import com.api.auth.application.AuthService
import com.api.auth.application.request.SignInRequest
import com.api.auth.application.request.SignUpRequest
import com.api.auth.ui.response.AuthResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) : AuthApi {

    @PostMapping("/sign-up")
    override fun signUp(request: SignUpRequest): ResponseEntity<AuthResponse> {
        val token = authService.signUp(request)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(AuthResponse(token))
    }

    @GetMapping("/sign-in")
    override fun signIn(request: SignInRequest): ResponseEntity<AuthResponse> {
        val token = authService.signIn(request)
        return ResponseEntity.ok(AuthResponse(token))
    }
}
