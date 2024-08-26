package com.kumaverse.kumabackend.category

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/v2/categories")
    fun findCategory(): List<CategoryEntity> {
        return categoryService.findApproved()
    }

}