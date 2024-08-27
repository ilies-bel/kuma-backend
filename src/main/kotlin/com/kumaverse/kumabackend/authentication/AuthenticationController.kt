package com.kumaverse.kumabackend.authentication

import com.kumaverse.kumabackend.user.UserEntity
import com.kumaverse.kumabackend.user.UserId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("/v2/auth")
class AuthenticationController(private val authenticationService: AuthenticationService) {
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody userToCreate: UserToCreate): UserId {
        return authenticationService.signup(userToCreate)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginUserDto: LoginUserDto): UserEntity {
        return authenticationService.authenticate(loginUserDto)
    }
}