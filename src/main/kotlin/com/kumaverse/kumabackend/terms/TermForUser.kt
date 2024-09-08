package com.kumaverse.kumabackend.terms

data class TermForUser(
    val term: Term,
    val userVote: Boolean?,
    val userHasBookmarked: Boolean,
)