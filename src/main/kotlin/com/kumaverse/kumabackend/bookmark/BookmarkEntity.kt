package com.kumaverse.kumabackend.bookmark

import com.kumaverse.kumabackend.terms.TermEntity
import com.kumaverse.kumabackend.user.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "bookmark")
class BookmarkEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,

    @ManyToOne
    var term: TermEntity,

    @ManyToOne
    var user: UserEntity,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BookmarkEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}