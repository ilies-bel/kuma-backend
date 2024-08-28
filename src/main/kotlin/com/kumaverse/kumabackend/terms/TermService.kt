package com.kumaverse.kumabackend.terms


import com.kumaverse.kumabackend.category.CategoryDao
import com.kumaverse.kumabackend.language.Language
import com.kumaverse.kumabackend.language.persistence.LanguageDao
import com.kumaverse.kumabackend.moderation.ApprovalStatus
import com.kumaverse.kumabackend.tag.TagDao
import com.kumaverse.kumabackend.user.People
import com.kumaverse.kumabackend.user.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class TermService(
    private val termDao: TermDao,
    private val tagDao: TagDao,
    private val languageDao: LanguageDao,
    private val categoryJpaDao: CategoryDao,
) {

    @Transactional
    fun addTerm(term: TermToCreateRequest): Long {
        val user = SecurityContextHolder.getContext().authentication.principal as UserEntity

        val tags = tagDao.findByNameOrCreate(listOf(term.theme))

        val language = languageDao.findByNameOrCreate(term.language)

        val grammaticalCategory = categoryJpaDao.findByNameOrCreate(term.grammaticalCategory)

        return termDao.save(
            TermEntity(
                name = term.term,
                defintion = term.definition,
                language = language,
                author = user,
                grammaticalCategory = grammaticalCategory,
                tags = tags,
                upvotes = 0,
                approvalStatus = ApprovalStatus.PENDING,
            )
        ).id!!
    }


    @Transactional
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


    fun findTermsForUser(pageable: Pageable, termSearchRequest: TermSearchRequest): Page<TermForUser> {
        val searchSpecification = termSearchRequest.toSpecification()

        val pendingSpecification = Specification.where<TermEntity> { root, _, cb ->
            cb.equal(root.get<String>(TermEntity::approvalStatus.name), ApprovalStatus.APPROVED.name)
        }

        return termDao.findAll(searchSpecification.and(pendingSpecification), pageable).map { term ->
            TermForUser(
                term = Term(
                    id = term.id!!,
                    term = term.name,
                    definition = term.defintion,
                    grammaticalCategory = GrammaticalCategory(
                        term.grammaticalCategory.id!!,
                        term.grammaticalCategory.name
                    ),
                    voteCount = term.upvotes,
                    author = People(term.author.id, term.author.name),
                    language = Language(term.language.id!!, term.language.name, term.language.code!!),
                    tags = term.tags.map { Tag(it.id, it.name) },
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

data class Tag(val id: Long, val name: String)

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
    val tags: List<Tag>,
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

