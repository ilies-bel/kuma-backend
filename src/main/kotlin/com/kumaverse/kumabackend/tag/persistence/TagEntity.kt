package com.kumaverse.kumabackend.tag.persistence

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

    var name: String,

    @Enumerated(EnumType.STRING)
    var approvalStatus: ApprovalStatus,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TagEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
