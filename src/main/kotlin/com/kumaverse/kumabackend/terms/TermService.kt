package com.kumaverse.kumabackend.terms

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service


@Service
class TermService(private val termDao: TermDao) {
    fun addTerm() {
        TODO()
    }

    fun findTermsPaginated() {
        TODO()
    }

    fun findTerms(pageable: Pageable): Page<TermEntity> {
        return termDao.findAll(pageable)
    }

    fun deleteTerm() {
        TODO()
    }


}

interface TermDao : JpaRepository<TermEntity, Long>

@Entity
@Table(name = "term")
class TermEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null
}
