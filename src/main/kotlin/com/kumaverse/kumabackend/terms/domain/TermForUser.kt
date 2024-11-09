package com.kumaverse.kumabackend.terms.domain

import com.kumaverse.kumabackend.terms.models.Term

data class TermForUser(
    val term: Term,
    val userVote: Boolean?,
    val userHasBookmarked: Boolean,
)