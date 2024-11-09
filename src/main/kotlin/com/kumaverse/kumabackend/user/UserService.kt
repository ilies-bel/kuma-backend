package com.kumaverse.kumabackend.user

import com.kumaverse.kumabackend.authentication.AuthenticationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserService(private val userDao: UserDao) {

    @Transactional(readOnly = true)
    fun getCurrent(): User {
        val currentUser = AuthenticationService.getUserFromContext() ?: throw NoSuchElementException("User not found")

        return User(
            id = currentUser.id,
            username = currentUser.username,
            role = currentUser.role,
        )
    }

    fun getUsers(page: Pageable): Page<UserEntity> {
        return userDao.findAll(page)
    }

    fun getById(id: String): UserEntity {
        TODO("Not yet implemented")
    }

    fun update(id: Long, userEntity: UserEntity): UserEntity {
        TODO("Not yet implemented")
    }

    fun bookMarkTerms(id: Long, termId: Long): UserEntity {
        TODO("Not yet implemented")
    }

    fun getBookmarkedTerms(id: Long): List<UserEntity> {
        TODO("Not yet implemented")
    }


}

data class People(
    val id: Long,
    val username: String,
)


@JvmInline
value class UserId(val value: Long)
