package com.kumaverse.kumabackend.terms

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TermDao : JpaRepository<TermEntity, Long>, JpaSpecificationExecutor<TermEntity>

