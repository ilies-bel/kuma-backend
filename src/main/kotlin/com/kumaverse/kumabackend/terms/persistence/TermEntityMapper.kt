package com.kumaverse.kumabackend.terms.persistence

import com.kumaverse.kumabackend.language.Language
import com.kumaverse.kumabackend.tag.Tag
import com.kumaverse.kumabackend.terms.models.GrammaticalCategory
import com.kumaverse.kumabackend.terms.models.Term
import com.kumaverse.kumabackend.user.People

fun TermEntity.toDomain(): Term {
    return Term(
        id = this.id,
        name = this.name,
        definition = this.definition,
        language = this.language.let { language ->
            Language(
                id = language.id!!,
                name = language.name,
                code = language.code!!,
            )
        },
        grammaticalCategory = this.grammaticalCategory.let {
            GrammaticalCategory(
                id = it.id,
                name = it.name,
            )
        },
        tags = this.tags.map {
            Tag(
                id = it.id,
                name = it.name,
            )
        },
        voteCount = this.upvotes,
        downVoteCount = this.downvotes,
        approvalStatus = this.approvalStatus,
        translation = this.translation,
        author = this.author.let {
            People(
                id = it.id,
                username = it.name,
            )
        },
    )
}