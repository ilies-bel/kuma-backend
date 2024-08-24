package com.kumaverse.kumabackend.language

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class LanguageController(private val languageService: LanguageService) {

    @GetMapping("/languages")
    fun findApproved() {
        languageService.findApproved()
    }

    @GetMapping("/languages/{id}")
    fun findById(@PathVariable id: Long) {
        languageService.findById(id)
    }


    @PostMapping("/languages")
    fun add(@RequestBody language: Language) {
        languageService.add(language)
    }

    fun update() {
        languageService.update()
    }

    fun delete() {
        languageService.delete()
    }

}