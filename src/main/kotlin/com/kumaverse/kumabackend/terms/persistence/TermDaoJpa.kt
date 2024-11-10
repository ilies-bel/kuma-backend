package com.kumaverse.kumabackend.terms.persistence

import com.kumaverse.kumabackend.terms.models.Term
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
class TermDao(private val termDaoJpa: TermDaoJpa) {
    fun findApprovedRandom(amount: Int): List<Term> {
        return termDaoJpa.findApprovedRandom(amount).map { it.toDomain() }
    }
}

interface TermDaoJpa : JpaRepository<TermEntity, Long>, JpaSpecificationExecutor<TermEntity> {

    @Query(nativeQuery = true, value = "SELECT *  FROM term ORDER BY random() LIMIT :limit")
    fun findApprovedRandom(limit: Int): List<TermEntity>
}

