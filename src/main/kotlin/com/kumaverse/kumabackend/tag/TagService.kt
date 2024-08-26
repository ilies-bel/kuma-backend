package com.kumaverse.kumabackend.tag

import com.kumaverse.kumabackend.language.persistence.LanguageEntity
import com.kumaverse.kumabackend.moderation.ApprovalStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class TagService(private val tagDao: TagDao) {
    fun findApprovedTags(page: Pageable): Page<TagEntity> {
        val approvedTags = Specification.where<TagEntity> { root, _, cb ->
            cb.equal(
                root.get<TagEntity>(LanguageEntity::approvalStatus.name),
                ApprovalStatus.APPROVED.name
            )
        }

        return tagDao.findAll(approvedTags, page)
    }

    fun addTheme(theme: TagEntity) {
        TODO()
    }


}


