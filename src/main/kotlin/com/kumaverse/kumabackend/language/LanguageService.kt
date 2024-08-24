package com.kumaverse.kumabackend.language

import com.kumaverse.kumabackend.language.persistence.LanguageDao
import com.kumaverse.kumabackend.language.persistence.LanguageEntity
import com.kumaverse.kumabackend.moderation.ApprovalStatus
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*


@Service
class LanguageService(private val languageDao: LanguageDao) {
    fun findApproved() {
        val approvedLanguages = Specification.where<LanguageEntity> { root, _, cb ->
            cb.equal(
                root.get<LanguageEntity>(LanguageEntity::approvalStatus.name),
                ApprovalStatus.APPROVED.name
            )
        }

        languageDao.findAll(approvedLanguages)
    }

    fun findById(id: Long): Optional<LanguageEntity> {
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
    var id: Long? = null,
    var approvalStatus: ApprovalStatus? = null,
)
