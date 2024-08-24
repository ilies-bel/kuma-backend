package com.kumaverse.kumabackend.terms

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class TermController(private val termService: TermService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = ["/api/terms", "/v2/terms"])
    fun getTerms(pageable: Pageable): Page<TermEntity> {
        return termService.findTerms(pageable)
    }
}