package com.kumaverse.kumabackend.category

import com.kumaverse.kumabackend.moderation.ApprovalStatus
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service


@Service
class CategoryService(private val categoryJpaDao: CategoryJpaDao) {
    fun findApproved(): List<CategoryEntity> {
        val spec = Specification<CategoryEntity> { root, _, cb ->
            cb.equal(
                root.get<CategoryEntity>(CategoryEntity::approvalStatus.name),
                ApprovalStatus.APPROVED.name
            )
        }

        return categoryJpaDao.findAll(spec)
    }

    fun addCategory() {
        TODO()
    }

    fun updateCategory() {
        TODO()
    }

    fun deleteCategory() {
        TODO()
    }

}