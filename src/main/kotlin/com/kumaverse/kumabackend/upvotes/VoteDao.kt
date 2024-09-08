package com.kumaverse.kumabackend.upvotes

import com.kumaverse.kumabackend.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VoteDao : JpaRepository<VoteEntity, Long> {
    fun findByUserAndIdIn(user: UserEntity, ids: Collection<Long>): List<VoteEntity>
    fun findByUserAndTermId(user: UserEntity, term: Long): VoteEntity?
}