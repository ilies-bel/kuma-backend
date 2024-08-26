package com.kumaverse.kumabackend.terms

import com.kumaverse.kumabackend.language.persistence.LanguageEntity
import com.kumaverse.kumabackend.user.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository

interface TermDao : JpaRepository<TermEntity, Long>

@Entity
@Table(name = "term")
class TermEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    var name: String,

    var defintion: String,

    @ManyToOne
    var language: LanguageEntity,

    var upvotes: Int,

    @ManyToOne
    var author: UserEntity,
)