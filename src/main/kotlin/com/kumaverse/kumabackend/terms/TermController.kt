package com.kumaverse.kumabackend.terms

import com.kumaverse.kumabackend.category.CategoryEntity
import com.kumaverse.kumabackend.language.persistence.LanguageEntity
import com.kumaverse.kumabackend.tag.TagEntity
import jakarta.persistence.criteria.JoinType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
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
    ): Page<TermForUser> {

        return termService.findTerms(pageable, TermSearchRequest(tag, language, category, searchTerm))
    }
}

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

            val predicates = listOfNotNull(tagPredicate, languagePredicate, categoryPredicate, searchTermPredicate)

            cb.and(*predicates.toTypedArray())
        }
    }
}