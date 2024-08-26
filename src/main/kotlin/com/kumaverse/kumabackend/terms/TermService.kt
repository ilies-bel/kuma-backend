package com.kumaverse.kumabackend.terms


import com.kumaverse.kumabackend.user.People
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class TermService(private val termDao: TermDao) {
    fun addTerm() {
        TODO()
    }


    fun patchTerm(patchTermRequest: PatchTermRequest) {
        patchTermRequest.term?.let {
            // update term
        }

        patchTermRequest.definition?.let {
            // update definition
        }


        patchTermRequest.grammaticalCategory?.let {
            // update grammatical category
        }


        patchTermRequest.theme?.let {
            // update theme
        }


        patchTermRequest.upvote?.let {
            // update upvote
        }
    }


    fun findTerms(pageable: Pageable): Page<Term> {
        return termDao.findAll(pageable).map {
            Term(
                id = it.id!!,
                term = it.name,
                definition = it.defintion,
                votes = it.upvotes,
                author = People(it.author.id, it.author.username)
            )
        }
    }

    fun deleteTerm() {
        TODO()
    }

}

data class PatchTermRequest(
    val term: String?,
    val definition: String?,
    val grammaticalCategory: String?,
    val theme: String?,
    val upvote: Boolean?,
    val bookmark: Boolean?,
)


data class Term(
    val id: Long,
    val term: String,
    val definition: String,
    val votes: Int,
    val author: People,
)

