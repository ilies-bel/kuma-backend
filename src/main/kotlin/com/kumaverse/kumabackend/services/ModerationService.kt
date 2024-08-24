package com.kumaverse.kumabackend.services

import org.springframework.stereotype.Service


@Service
class ModerationService {


    /**
     * Add comment
     */
    fun updateApproval(identifier: Long, status: ApprovalStatus, type: Approvable) {
        TODO()
    }

    fun finPendingThemes() {
        TODO()
    }

    fun findPendingCategories() {
        TODO()
    }

    fun findPendingTerms() {
        TODO()
    }
}

enum class Approvable {
    QUIZ,
    FLASHCARD,
    TERM,
    THEME,
    ;
}

enum class ApprovalStatus {
    PENDING,
    APPROVED,
    REJECTED,
    TO_UPDATE,
    ;
}