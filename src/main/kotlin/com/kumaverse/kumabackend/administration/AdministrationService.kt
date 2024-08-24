package com.kumaverse.kumabackend.administration

import com.kumaverse.kumabackend.dao.UserDao
import com.kumaverse.kumabackend.services.UserId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdministrationService(private val userDao: UserDao) {

    @Transactional
    fun banUser(id: Long): UserId {
        return userDao.getReferenceById(id).apply {
            accountStatus = AccountStatus.BANNED
        }.let { UserId(it.id) }
    }


    @Transactional
    fun changeRole(id: Long, role: Role): UserId {
        userDao.getReferenceById(id).apply {
            this.role = role
        }.let { return UserId(it.id) }
    }
}


enum class Role {
    ADMIN,
    MODERATOR,
    USER,
    ;
}

enum class AccountStatus {
    BANNED,
    ACTIVE,
    SUSPENDED,
    ;
}