package com.kumaverse.kumabackend.terms

import com.kumaverse.kumabackend.bookmark.BookmarkEntity
import com.kumaverse.kumabackend.category.CategoryEntity
import com.kumaverse.kumabackend.language.persistence.LanguageEntity
import com.kumaverse.kumabackend.moderation.ApprovalStatus
import com.kumaverse.kumabackend.tag.persistence.TagEntity
import com.kumaverse.kumabackend.user.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "term")
class TermEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,

    var name: String,

    var definition: String,

    @ManyToOne
    var language: LanguageEntity,

    var upvotes: Int,

    @ManyToMany
    @JoinTable(
        name = "tag_term",
        joinColumns = [jakarta.persistence.JoinColumn(name = "term_id")],
        inverseJoinColumns = [jakarta.persistence.JoinColumn(name = "tag_id")]
    )
    var tags: List<TagEntity>,

    @ManyToOne
    var author: UserEntity,

    @ManyToOne
    var grammaticalCategory: CategoryEntity,

    @Enumerated(EnumType.STRING)
    var approvalStatus: ApprovalStatus,

    @OneToMany(mappedBy = "term")
    val bookmarks: List<BookmarkEntity>,

    val translation: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TermEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}