package com.kumaverse.kumabackend.terms.domain


import com.kumaverse.kumabackend.authentication.AuthenticationService
import com.kumaverse.kumabackend.bookmark.BookmarkEntity
import com.kumaverse.kumabackend.bookmark.BookmarkJpaDao
import com.kumaverse.kumabackend.category.CategoryDao
import com.kumaverse.kumabackend.language.persistence.LanguageDao
import com.kumaverse.kumabackend.moderation.ApprovalStatus
import com.kumaverse.kumabackend.tag.persistence.TagDao
import com.kumaverse.kumabackend.terms.persistence.TermDao
import com.kumaverse.kumabackend.terms.persistence.TermEntity
import com.kumaverse.kumabackend.terms.presentation.PatchTermRequest
import com.kumaverse.kumabackend.terms.presentation.TermToCreateRequest
import com.kumaverse.kumabackend.upvotes.VoteDao
import com.kumaverse.kumabackend.upvotes.VoteEntity
import com.kumaverse.kumabackend.user.UserEntity
import com.kumaverse.kumabackend.user.UserService
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
    private val bookmarkJpaDao: BookmarkJpaDao,
    private val upvoteDao: VoteDao,
    private val mapper: TermMapper,
    private val userService: UserService,
) {

    @Transactional
    fun addTerm(term: TermToCreateRequest): Long {
        val user = SecurityContextHolder.getContext().authentication.principal as UserEntity

        val tags = tagDao.findByNameOrCreate(listOf(term.theme))

        val language = languageDao.findByNameOrCreate(term.language)

        val grammaticalCategory = categoryJpaDao.findByNameOrCreate(term.grammaticalCategory)

        return termDao.save(
            TermEntity(
                id = -1,
                name = term.term,
                definition = term.definition,
                language = language,
                author = user,
                grammaticalCategory = grammaticalCategory,
                tags = tags,
                upvotes = 0,
                approvalStatus = ApprovalStatus.PENDING,
                translation = term.translation,
                bookmarks = emptyList(),
                downvotes = 0
            )
        ).id
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


    @Transactional(readOnly = true)
    fun findTermsForUser(pageable: Pageable, termSearchRequest: Specification<TermEntity>): Page<TermForUser> {

        val user = AuthenticationService.getUserFromContext()


        val pendingSpecification = Specification.where<TermEntity> { root, _, cb ->
            cb.equal(root.get<String>(TermEntity::approvalStatus.name), ApprovalStatus.APPROVED.name)
        }


        val foundTerms = termDao.findAll(termSearchRequest.and(pendingSpecification), pageable)

        var bookmarkedTermIds = emptyList<Long>()
        var votes = emptyList<VoteEntity>()

        if (user != null) {
            bookmarkedTermIds =
                bookmarkJpaDao.findByUserAndIdIn(user, foundTerms.content.map { it.id })
                    .map { it.term.id }

            votes = upvoteDao.findByUserAndIdIn(user, foundTerms.content.map { it.id })
        }

        return foundTerms.map { term ->
            mapper.mapToTermForUser(
                term,
                votes.find { it.term.id == term.id },
                bookmarkedTermIds.contains(term.id.toLong())
            )
        }


    }

    fun deleteTerm() {
        TODO()
    }

    @Transactional(readOnly = true)
    fun findBookmarkedTerms(pageable: Pageable, id: Long): Page<TermForUser> {
        val bookmarkedSpecification = Specification.where<TermEntity> { root, _, cb ->
            cb.equal(
                root.get<BookmarkEntity>(TermEntity::bookmarks.name).get<Long>(BookmarkEntity::user.name)
                    .get<Long>(UserEntity::id.name), id
            )
        }

        return findTermsForUser(pageable, bookmarkedSpecification)
    }

    fun addBookmark(termId: Long): Long {
        val user = SecurityContextHolder.getContext().authentication.principal as UserEntity

        val term = termDao.findById(termId).orElseThrow { throw IllegalArgumentException("Term not found") }

        bookmarkJpaDao.save(BookmarkEntity(-1, term, user))

        return termId
    }


    fun removeBookmark(termId: Long): Long {
        val user = SecurityContextHolder.getContext().authentication.principal as UserEntity

        val term = termDao.findById(termId).orElseThrow { throw IllegalArgumentException("Term not found") }

        bookmarkJpaDao.deleteByTermAndUser(term, user)

        return termId
    }

    fun getTermById(termId: Long): TermForUser {
        val user = AuthenticationService.getUserFromContext()

        val term = termDao.findById(termId).orElseThrow { throw IllegalArgumentException("Term not found") }

        val userVote = user?.let { upvoteDao.findByUserAndTermId(it, term.id.toLong()) }
        val userHasBookmarked = user?.let { bookmarkJpaDao.existsByUserAndTerm(it, term) } ?: false

        return mapper.mapToTermForUser(
            term,
            userVote,
            userHasBookmarked
        )
    }


}


