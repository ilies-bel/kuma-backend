package com.kumaverse.kumabackend.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserDao : JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    fun findByName(name: String): UserEntity?
}


