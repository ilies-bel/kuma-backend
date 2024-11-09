package com.kumaverse.kumabackend.language.persistence

import com.kumaverse.kumabackend.moderation.ApprovalStatus
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository


@Repository
class LanguageDao(private val languageJpaDao: LanguageJpaDao) {

    fun findById(id: Long): LanguageEntity? = languageJpaDao.findById(id).orElse(null)
    fun findAll(spec: Specification<LanguageEntity>): List<LanguageEntity> =
        languageJpaDao.findAll(spec)

    fun findByNameOrCreate(language: String): LanguageEntity {
        if (language.isEmpty()) {
            throw IllegalArgumentException("Language cannot be empty")
        }


        return languageJpaDao.findByNameIgnoreCase(language) ?: languageJpaDao.save(
            LanguageEntity(
                name = language,
                code = null,
                approvalStatus = ApprovalStatus.PENDING,
            )
        )
    }
}

interface LanguageJpaDao : JpaRepository<LanguageEntity, Long>, JpaSpecificationExecutor<LanguageEntity> {
    fun findByNameIgnoreCase(name: String): LanguageEntity?

}