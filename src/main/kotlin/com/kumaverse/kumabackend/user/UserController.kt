package com.kumaverse.kumabackend.user

import com.kumaverse.kumabackend.administration.Role
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/v2/users/me")
    fun getCurrent(): User {
        return userService.getCurrent()
    }

    @GetMapping("/users")
    fun getUsers(@PageableDefault pageableDefault: Pageable): Page<UserEntity> {
        return userService.getUsers(pageableDefault)
    }


    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: String): UserEntity {
        return userService.getById(id)
    }


    @PatchMapping("/users/{id}")
    fun updateUserProfile(id: Long, userEntity: UserEntity): UserEntity {
        return userService.update(id, userEntity)
    }
}


data class User(
    val id: Long,
    val username: String,
    val role: Role,
)