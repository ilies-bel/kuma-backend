package com.kumaverse.kumabackend.category

import com.kumaverse.kumabackend.moderation.ApprovalStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository


@Repository
class CategoryDao(private val categoryJpaDao: CategoryJpaDao) {
    fun findByNameOrCreate(categoryName: String): CategoryEntity {
        return categoryJpaDao.findByName(categoryName.lowercase()) ?: categoryJpaDao.save(
            CategoryEntity(
                name = categoryName,
                approvalStatus = ApprovalStatus.PENDING
            )
        )
    }
}

interface CategoryJpaDao : JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
    fun findByName(name: String): CategoryEntity?
}