package com.kumaverse.kumabackend.services

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.ui.context.Theme

@Service
class ThemeService {
    fun findThemes(): Page<Tag> {
        TODO()
    }

    fun addTheme(theme: Theme) {
        TODO()
    }


}


@Entity
class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    val id: Long = 0
}