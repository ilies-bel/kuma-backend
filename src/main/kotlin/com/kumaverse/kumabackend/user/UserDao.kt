package com.kumaverse.kumabackend.user

import com.kumaverse.kumabackend.administration.AccountStatus
import com.kumaverse.kumabackend.administration.Role
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserDao : JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity>

@Entity
@Table(name = "people")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Enumerated(EnumType.STRING)
    var accountStatus: AccountStatus,

    var username: String,

    @Enumerated(EnumType.STRING)
    var role: Role,

    ) {
    companion object {
        fun fromUserToCreate(userToCreate: UserToCreate): UserEntity {
            return UserEntity(
                id = 0,
                accountStatus = AccountStatus.ACTIVE,
                role = Role.USER,
                username = userToCreate.username
            )
        }
    }
}


