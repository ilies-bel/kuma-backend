package com.kumaverse.kumabackend.administration

import com.kumaverse.kumabackend.services.UserId
import com.kumaverse.kumabackend.services.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class AdministrationController(
    private val userService: UserService,
    private val administrationService: AdministrationService,
) {

    @DeleteMapping("/users/{id}")
    fun banUser(@PathVariable id: Long): UserId {
        return administrationService.banUser(id)
    }

    @PatchMapping("/users/{id}/roles/{role}")
    fun changeRole(@PathVariable id: Long, @PathVariable role: String): UserId { // TO admin / Moderator
        return administrationService.changeRole(
            id, Role.valueOf(role)
        )
    }
}


