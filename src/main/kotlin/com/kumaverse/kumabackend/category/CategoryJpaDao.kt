package com.kumaverse.kumabackend.category

import com.kumaverse.kumabackend.moderation.ApprovalStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository


@Repository
class CategoryDao(private val categoryJpaDao: CategoryJpaDao) {
    fun findByNameOrCreate(categoryName: String): CategoryEntity {
        if (categoryName.isEmpty()) {
            throw IllegalArgumentException("Category name cannot be empty")
        }

        return categoryJpaDao.findByName(categoryName.lowercase()) ?: categoryJpaDao.save(
            CategoryEntity(
                id = -1,
                name = categoryName,
                approvalStatus = ApprovalStatus.PENDING
            )
        )
    }
}

interface CategoryJpaDao : JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
    fun findByName(name: String): CategoryEntity?
}