package com.kumaverse.kumabackend.bookmark

import com.kumaverse.kumabackend.upvotes.VoteEntity
import com.kumaverse.kumabackend.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkJpaDao : JpaRepository<VoteEntity, Long> {
    fun findByUserAndIdIn(user: UserEntity, ids: Collection<Long>): List<VoteEntity>
}