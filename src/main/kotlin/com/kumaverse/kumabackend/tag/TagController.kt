package com.kumaverse.kumabackend.tag

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TagController(private val tagService: TagService) {


    @GetMapping("/v2/tags")
    fun findTag(page: Pageable) {
        tagService.findApprovedTags(page)
    }

    fun addTag() {
        TODO()
    }

    fun updateTag() {
        TODO()
    }

    fun deleteTag() {
        TODO()
    }


}