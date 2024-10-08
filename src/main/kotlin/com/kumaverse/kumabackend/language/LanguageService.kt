package com.kumaverse.kumabackend.language

import com.kumaverse.kumabackend.language.persistence.LanguageDao
import com.kumaverse.kumabackend.language.persistence.LanguageEntity
import com.kumaverse.kumabackend.moderation.ApprovalStatus
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service


@Service
class LanguageService(private val languageDao: LanguageDao) {

    fun findApproved(): List<LanguageEntity> {
        val approvedLanguages = Specification.where<LanguageEntity> { root, _, cb ->
            cb.equal(
                root.get<LanguageEntity>(LanguageEntity::approvalStatus.name),
                ApprovalStatus.APPROVED.name
            )
        }

        return languageDao.findAll(approvedLanguages)
    }

    fun findById(id: Long): LanguageEntity? {
        return languageDao.findById(id)
    }

    fun add(languageToCreate: Language) {

    }

    fun update() {
        TODO()
    }

    fun delete() {
        TODO()
    }

}

data class Language(
    val id: Long,
    val name: String,
    val code: String,
)
