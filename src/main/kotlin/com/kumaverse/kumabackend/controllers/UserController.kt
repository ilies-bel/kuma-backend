package com.kumaverse.kumabackend.controllers

import com.kumaverse.kumabackend.administration.AdministrationService
import com.kumaverse.kumabackend.dao.UserEntity
import com.kumaverse.kumabackend.services.UserId
import com.kumaverse.kumabackend.services.UserService
import com.kumaverse.kumabackend.services.UserToCreate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(private val userService: UserService, private val administrationService: AdministrationService) {

    @PostMapping("/users")
    fun saveUser(userToCreate: UserToCreate): UserId {
        return userService.save(userToCreate)
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


