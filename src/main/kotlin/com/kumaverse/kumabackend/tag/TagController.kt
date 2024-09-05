package com.kumaverse.kumabackend.tag

import com.kumaverse.kumabackend.tag.persistence.TagEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TagController(private val tagService: TagService) {


    @GetMapping("/v2/public/tags")
    fun findApprovedTag(): List<TagEntity> {
        return tagService.findApprovedTags()
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