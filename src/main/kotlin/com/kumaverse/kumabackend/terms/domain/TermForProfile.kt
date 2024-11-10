package com.kumaverse.kumabackend.terms.domain

import com.kumaverse.kumabackend.terms.models.Term
import com.kumaverse.kumabackend.terms.persistence.TermDaoJpa
import com.kumaverse.kumabackend.terms.persistence.TermEntity
import com.kumaverse.kumabackend.terms.persistence.toDomain
import com.kumaverse.kumabackend.user.UserEntity
import com.kumaverse.kumabackend.user.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class TermForProfileService(
    private val termDaoJpa: TermDaoJpa,
    private val userService: UserService,
) {
    fun findAuthoredTerms(pageable: Pageable): Page<Term> {
        val user = userService.getCurrent()

        val authoredSpecification = Specification.where<TermEntity> { root, _, cb ->
            cb.equal(root.get<UserEntity>(TermEntity::author.name).get<Long>(UserEntity::id.name), user.id)
        }

        return termDaoJpa.findAll(authoredSpecification, pageable).map(TermEntity::toDomain)
    }
}
