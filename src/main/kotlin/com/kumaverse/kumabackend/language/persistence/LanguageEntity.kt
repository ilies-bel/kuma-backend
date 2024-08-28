package com.kumaverse.kumabackend.language.persistence

import com.kumaverse.kumabackend.moderation.ApprovalStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "language")
class LanguageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    var code: String?,

    @Enumerated(EnumType.STRING)
    var approvalStatus: ApprovalStatus,

    )
