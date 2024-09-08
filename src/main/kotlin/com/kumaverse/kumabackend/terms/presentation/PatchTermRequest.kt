package com.kumaverse.kumabackend.terms.presentation

data class PatchTermRequest(
    val term: String?,
    val definition: String?,
    val grammaticalCategory: String?,
    val theme: String?,
    val upvote: Boolean?,
    val bookmark: Boolean?,
)