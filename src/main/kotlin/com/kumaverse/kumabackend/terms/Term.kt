package com.kumaverse.kumabackend.terms

import com.kumaverse.kumabackend.language.Language
import com.kumaverse.kumabackend.tag.Tag
import com.kumaverse.kumabackend.user.People

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