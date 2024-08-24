package com.kumaverse.kumabackend.services

import com.kumaverse.kumabackend.administration.Role
import com.kumaverse.kumabackend.dao.UserDao
import com.kumaverse.kumabackend.dao.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Service
class UserService(private val userDao: UserDao, private val bookmarkDao: BookmarkDao) {

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

    fun save(userToCreate: UserToCreate): UserId {
        return UserId(userDao.save(UserEntity.fromUserToCreate(userToCreate)).id)
    }
}


@Repository
class BookmarkDao {
    fun bookMarkTerm(id: Long, termId: Long): UserEntity {
        TODO()
    }
}


data class UserToCreate(
    val username: String,
    val email: String,
)

data class User(
    val id: UserId,
    val isBanned: Boolean,
    val role: Role,
)

@JvmInline
value class UserId(val value: Long)
