package com.kumaverse.kumabackend.quiz

import com.kumaverse.kumabackend.terms.models.Term
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizController(private val quizService: QuizService) {
    @PostMapping("/v2/quizzes")
    fun generateQuiz(): List<Term> {
        return quizService.generateQuiz()
    }
}