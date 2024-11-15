package com.kumaverse.kumabackend.bookmark

import com.kumaverse.kumabackend.terms.persistence.TermEntity
import com.kumaverse.kumabackend.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkJpaDao : JpaRepository<BookmarkEntity, Long> {
    fun findByUserAndIdIn(user: UserEntity, ids: Collection<Long>): List<BookmarkEntity>
    fun deleteByTermAndUser(term: TermEntity, user: UserEntity)
    fun existsByUserAndTerm(user: UserEntity, term: TermEntity): Boolean
}