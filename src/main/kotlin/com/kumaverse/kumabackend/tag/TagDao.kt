package com.kumaverse.kumabackend.tag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TagDao : JpaRepository<TagEntity, Long>, JpaSpecificationExecutor<TagEntity>