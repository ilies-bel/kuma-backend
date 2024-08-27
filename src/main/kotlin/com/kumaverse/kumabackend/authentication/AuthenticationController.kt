package com.kumaverse.kumabackend.authentication

import com.kumaverse.kumabackend.user.UserId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v2/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
    private val jwtService: JwtService,
) {
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody userToCreate: UserToCreate): UserId {
        return authenticationService.signup(userToCreate)
    }

    @PostMapping("/login")
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