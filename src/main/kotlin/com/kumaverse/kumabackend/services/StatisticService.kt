package com.kumaverse.kumabackend.services

class StatisticService {
    fun countUserBase(): UserBaseStats {
        TODO()
    }

    fun countTermStats(): TermStats {
        TODO()
    }

    fun countQuizStats() {
        TODO()
    }
}


data class TermStats(
    val totalTerms: Int,
    val rejectedTerms: Int,
    val deletedTerms: Int,
)


data class UserBaseStats(
    val totalUsers: Int,
    val activeUsers: Int,
    val bannedUsers: Int,
)



