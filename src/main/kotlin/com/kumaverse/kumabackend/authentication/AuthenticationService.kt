package com.kumaverse.kumabackend.authentication

import com.kumaverse.kumabackend.administration.AccountStatus
import com.kumaverse.kumabackend.administration.Role
import com.kumaverse.kumabackend.user.UserDao
import com.kumaverse.kumabackend.user.UserEntity
import com.kumaverse.kumabackend.user.UserId
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthenticationService(
    private val userRepository: UserDao,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
) {

    fun signup(input: UserToCreate): UserId {
        return userRepository.save(
            UserEntity(
                id = 0,
                name = input.username,
                role = Role.USER,
                hashedPassword = passwordEncoder.encode(input.password),
                accountStatus = AccountStatus.ACTIVE,
            )
        ).id.let { UserId(it) }
    }

    fun authenticate(input: LoginUserDto): UserEntity {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                input.username,
                input.password,
            )
        )

        return userRepository.findByName(input.username) ?: throw IllegalArgumentException("User not found")
    }
}

data class User(
    val id: UserId,
    val isBanned: Boolean,
    val role: Role,
)


data class UserToCreate(
    val username: String,
    val email: String,
    val password: String,
)

