package com.kumaverse.kumabackend.terms.domain

import com.kumaverse.kumabackend.language.Language
import com.kumaverse.kumabackend.tag.Tag
import com.kumaverse.kumabackend.terms.models.GrammaticalCategory
import com.kumaverse.kumabackend.terms.models.Term
import com.kumaverse.kumabackend.terms.persistence.TermEntity
import com.kumaverse.kumabackend.upvotes.VoteEntity
import com.kumaverse.kumabackend.user.People
import org.springframework.stereotype.Service

@Service
class TermMapper {
    fun mapToTermForUser(term: TermEntity, userVote: VoteEntity?, hasBookmarked: Boolean?): TermForUser {
        return TermForUser(
            term = Term(
                id = term.id,
                name = term.name,
                definition = term.definition,
                grammaticalCategory = GrammaticalCategory(
                    term.grammaticalCategory.id,
                    term.grammaticalCategory.name
                ),
                voteCount = term.upvotes,
                author = People(term.author.id, term.author.name),
                language = Language(term.language.id!!, term.language.name, term.language.code!!),
                tags = term.tags.map { Tag(it.id, it.name) },
                translation = term.translation,
                approvalStatus = term.approvalStatus,
                downVoteCount = term.downvotes,
            ),
            userVote = userVote?.isUpvote,
            userHasBookmarked = hasBookmarked ?: false,
        )
    }
}