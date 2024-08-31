package com.kumaverse.kumabackend.authentication

import com.kumaverse.kumabackend.administration.AccountStatus
import com.kumaverse.kumabackend.administration.Role
import com.kumaverse.kumabackend.user.UserDao
import com.kumaverse.kumabackend.user.UserEntity
import com.kumaverse.kumabackend.user.UserId
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
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
                id = -1,
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

        return userRepository.findByName(input.username)
            ?: throw IllegalArgumentException("User not found")
    }

    companion object {
        fun getUserFromContext(): UserEntity? {
            val user = SecurityContextHolder.getContext().authentication.principal
            if (user == "anonymousUser") {
                return null
            }

            return user as UserEntity
        }
    }
}

data class AuthenticatedUser(
    val id: UserId,
    val username: String,
    val role: Role,
)


data class UserToCreate(
    val username: String,
    val password: String,
)

