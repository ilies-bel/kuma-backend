package com.kumaverse.kumabackend.tag

import com.kumaverse.kumabackend.moderation.ApprovalStatus
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
class TagDao(private val tagJpaDao: TagJpaDao) {

    fun findAll(spec: Specification<TagEntity>): List<TagEntity> =
        tagJpaDao.findAll(spec)

    fun findByNameOrCreate(tags: List<String>): List<TagEntity> {
        return tags.map { tag ->
            tagJpaDao.findByName(tag.lowercase()) ?: tagJpaDao.save(
                TagEntity(
                    name = tag,
                    approvalStatus = ApprovalStatus.PENDING
                )
            )
        }
    }
}

interface TagJpaDao : JpaRepository<TagEntity, Long>, JpaSpecificationExecutor<TagEntity> {
    fun findByName(name: String): TagEntity?
}