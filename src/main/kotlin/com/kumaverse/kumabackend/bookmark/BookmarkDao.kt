package com.kumaverse.kumabackend.bookmark

import com.kumaverse.kumabackend.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkJpaDao : JpaRepository<BookmarkEntity, Long> {
    fun findByUser(user: UserEntity): List<BookmarkEntity>
}