package com.kumaverse.kumabackend.terms.presentation

import com.kumaverse.kumabackend.category.CategoryEntity
import com.kumaverse.kumabackend.language.persistence.LanguageEntity
import com.kumaverse.kumabackend.tag.persistence.TagEntity
import com.kumaverse.kumabackend.terms.persistence.TermEntity
import jakarta.persistence.criteria.JoinType
import org.springframework.data.jpa.domain.Specification


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