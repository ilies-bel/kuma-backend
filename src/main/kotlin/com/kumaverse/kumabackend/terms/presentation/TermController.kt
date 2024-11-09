package com.kumaverse.kumabackend.terms.presentation

import com.kumaverse.kumabackend.terms.domain.TermForProfileService
import com.kumaverse.kumabackend.terms.domain.TermForUser
import com.kumaverse.kumabackend.terms.domain.TermService
import com.kumaverse.kumabackend.terms.models.Term
import com.kumaverse.kumabackend.user.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class TermController(private val termService: TermService, private val termForProfileService: TermForProfileService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/v2/public/terms")
    fun getTerms(
        pageable: Pageable,
        @RequestParam tag: String?,
        @RequestParam language: String?,
        @RequestParam category: String?,
        @RequestParam searchTerm: String?,
        @RequestParam bookmarked: Boolean?,
        @AuthenticationPrincipal user: UserEntity?,
    ): Page<TermForUser> {
        if (bookmarked != null && user != null) {
            return termService.findBookmarkedTerms(pageable, user.id)
        }

        return termService.findTermsForUser(
            pageable,
            TermSearchRequest(tag, language, category, searchTerm).toSpecification()
        )
    }

    @GetMapping("/v2/public/terms/{termId}")
    fun getTermById(@PathVariable termId: Long): TermForUser {
        return termService.getTermById(termId)
    }

    @GetMapping("/v2/terms/authored")
    fun getAuthoredTerms(pageable: Pageable): Page<Term> {
        return termForProfileService.findAuthoredTerms(pageable)
    }


    @PostMapping("/v2/terms")
    @ResponseStatus(HttpStatus.CREATED)
    fun addTerm(@RequestBody term: TermToCreateRequest): Long {
        return termService.addTerm(term)
    }


    @PostMapping("/v2/terms/{termId}/bookmarks")
    @ResponseStatus(HttpStatus.CREATED)
    fun bookmark(@PathVariable termId: Long): Long {
        return termService.addBookmark(termId)
    }

    @DeleteMapping("/v2/terms/{termId}/bookmarks")
    fun deleteBookmark(@PathVariable termId: Long): Long {
        return termService.removeBookmark(termId)
    }
}
