package com.kumaverse.kumabackend.terms

import org.springframework.data.jpa.repository.JpaRepository

interface TermDao : JpaRepository<TermEntity, Long>

