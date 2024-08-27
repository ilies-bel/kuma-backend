package com.kumaverse.kumabackend.authentication

import com.kumaverse.kumabackend.user.UserId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class AuthenticationController(
    private val authenticationService: AuthenticationService,
    private val jwtService: JwtService,
) {
    @PostMapping("/v2/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody userToCreate: UserToCreate): UserId {
        return authenticationService.signup(userToCreate)
    }

    @PostMapping("/v2/auth/login")
    fun login(@RequestBody loginUserDto: LoginUserDto): LoginResponse {
        val user = authenticationService.authenticate(loginUserDto)

        val jwtToken: String = jwtService.generateToken(user)

        return LoginResponse(jwtToken, jwtService.getExpirationTime())
    }
}

data class LoginResponse(
    var token: String,
    var expiresIn: Long,
)