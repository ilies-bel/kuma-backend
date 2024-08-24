package com.kumaverse.kumabackend.language.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface LanguageDao : JpaRepository<LanguageEntity, Long>, JpaSpecificationExecutor<LanguageEntity>