package com.kumaverse.kumabackend.quiz

import com.kumaverse.kumabackend.terms.models.Term
import com.kumaverse.kumabackend.terms.persistence.TermDao
import org.springframework.stereotype.Service


@Service
class QuizService(private val termDao: TermDao) {
    fun generateQuiz(): List<Term> {

        val amount = 10
        return termDao.findApprovedRandom(amount)
    }

}