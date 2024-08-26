package com.kumaverse.kumabackend.terms


import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class TermService(private val termDao: TermDao) {
    fun addTerm() {
        TODO()
    }


    fun findTerms(pageable: Pageable): Page<Term> {
        return termDao.findAll(pageable).map {
            Term(
                id = it.id!!,
                term = it.name,
                definition = it.defintion,
                votes = it.upvotes
            )
        }
    }

    fun deleteTerm() {
        TODO()
    }

}


data class Term(
    val id: Long,
    val term: String,
    val definition: String,
    val votes: Int,
)


