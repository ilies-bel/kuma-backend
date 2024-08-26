package com.kumaverse.kumabackend.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CategoryDao : JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity>