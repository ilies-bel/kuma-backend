package com.kumaverse.kumabackend.tag

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
@Table(name = "tag")
class TagEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    var approvalStatus: ApprovalStatus,
)