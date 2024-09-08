package com.kumaverse.kumabackend.terms.presentation

import com.kumaverse.kumabackend.category.CategoryEntity
import com.kumaverse.kumabackend.language.persistence.LanguageEntity
import com.kumaverse.kumabackend.tag.persistence.TagEntity
import com.kumaverse.kumabackend.terms.TermEntity
import com.kumaverse.kumabackend.terms.TermForUser
import com.kumaverse.kumabackend.terms.TermService
import com.kumaverse.kumabackend.user.UserEntity
import jakarta.persistence.criteria.JoinType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class TermController(private val termService: TermService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/v2/public/terms")
    fun getTerms(
        pageable: Pageable,
        @RequestParam tag: String?,
        @RequestParam language: String?,
        @RequestParam category: String?,
        @RequestParam searchTerm: String?,
        @RequestParam bookmarked: Boolean?,
        @AuthenticationPrincipal user: UserEntity?,
    ): Page<TermForUser> {
        if (bookmarked != null && user != null) {
            return termService.findBookmarkedTerms(pageable, user.id)
        }

        return termService.findTermsForUser(
            pageable,
            TermSearchRequest(tag, language, category, searchTerm).toSpecification()
        )
    }


    @GetMapping("/v2/public/terms/{termId}")
    fun getTermById(@PathVariable termId: Long): TermForUser {
        return termService.getTermById(termId)
    }


    @PostMapping("/v2/terms")
    @ResponseStatus(HttpStatus.CREATED)
    fun addTerm(@RequestBody term: TermToCreateRequest): Long {
        return termService.addTerm(term)
    }


    @PostMapping("/v2/terms/{termId}/bookmarks")
    @ResponseStatus(HttpStatus.CREATED)
    fun bookmark(@PathVariable termId: Long): Long {
        return termService.addBookmark(termId)
    }

    @DeleteMapping("/v2/terms/{termId}/bookmarks")
    fun deleteBookmark(@PathVariable termId: Long): Long {
        return termService.removeBookmark(termId)
    }
}

data class TermToCreateRequest(
    val term: String,
    val translation: String,
    val definition: String,
    val grammaticalCategory: String,
    val theme: String,
    val language: String,
)


data class TermSearchRequest(
    val tag: String?,
    val language: String?,
    val category: String?,
    val searchTerm: String?,
) {
    fun toSpecification(): Specification<TermEntity> {

        return Specification.where { root, _, cb ->
            // create a  criteria on the tag name

            val tagPredicate = tag?.let {
                val joinTags =
                    root.join<TagEntity, TermEntity>(TermEntity::tags.name, JoinType.LEFT)

                cb.equal(joinTags.get<String>(TagEntity::name.name), tag)
            }

            val languagePredicate = language?.let {
                val joinLanguage =
                    root.join<TermEntity, LanguageEntity>(TermEntity::language.name, JoinType.LEFT).get<String>("name")

                cb.equal(joinLanguage, language)
            }

            val categoryPredicate = category?.let {
                cb.equal(
                    root.get<String>(TermEntity::grammaticalCategory.name).get<String>(CategoryEntity::name.name),
                    category
                )
            }

            val searchTermPredicate = searchTerm?.let {
                cb.like(root.get(TermEntity::name.name), "%$searchTerm%")
            }

            val predicates =
                listOfNotNull(tagPredicate, languagePredicate, categoryPredicate, searchTermPredicate)

            cb.and(*predicates.toTypedArray())
        }
    }
}