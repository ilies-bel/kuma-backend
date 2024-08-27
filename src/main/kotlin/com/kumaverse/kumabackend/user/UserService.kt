package com.kumaverse.kumabackend.user

import com.kumaverse.kumabackend.bookmark.BookmarkDao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
}

data class People(
    val id: Long,
    val username: String,
)


@JvmInline
value class UserId(val value: Long)
