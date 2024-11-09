package com.kumaverse.kumabackend.category

import com.kumaverse.kumabackend.moderation.ApprovalStatus
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
    var id: Long,

    var name: String,

    @Enumerated(EnumType.STRING)
    var approvalStatus: ApprovalStatus,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CategoryEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}