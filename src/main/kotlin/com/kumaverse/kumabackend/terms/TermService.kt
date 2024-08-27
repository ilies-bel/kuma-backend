package com.kumaverse.kumabackend.terms


import com.kumaverse.kumabackend.language.Language
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


    fun findTerms(pageable: Pageable, termSearchRequest: TermSearchRequest): Page<TermForUser> {
        // get users data from user service

        val specification = termSearchRequest.toSpecification()

        return termDao.findAll(specification, pageable).map {
            TermForUser(
                term = Term(
                    id = it.id!!,
                    term = it.name,
                    definition = it.defintion,
                    grammaticalCategory = GrammaticalCategory(it.grammaticalCategory.id!!, it.grammaticalCategory.name),
                    voteCount = it.upvotes,
                    author = People(it.author.id!!, it.author.name),
                    language = Language(it.language.id!!, it.language.name, it.language.code),
                    tags = emptyList(),
                    translation = "", // TODO FIXME
                ),
                userVote = true, // TODO FIXME
                userHasBookmarked = false,
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
    val grammaticalCategory: GrammaticalCategory,
    val tags: List<String>,
    val definition: String,
    val translation: String,
    val voteCount: Int,
    val author: People,
    val language: Language,
)

data class GrammaticalCategory(val id: Long, val name: String)

data class TermForUser(
    val term: Term,
    val userVote: Boolean,
    val userHasBookmarked: Boolean,
)

