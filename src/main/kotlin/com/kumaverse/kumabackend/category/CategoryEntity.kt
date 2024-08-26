package com.kumaverse.kumabackend.category

import com.kumaverse.kumabackend.moderation.ApprovalStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "grammatical_category")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    var name: String,

    @Enumerated(EnumType.STRING)
    var approvalStatus: ApprovalStatus,
)