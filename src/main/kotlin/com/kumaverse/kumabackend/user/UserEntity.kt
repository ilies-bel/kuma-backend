package com.kumaverse.kumabackend.user

import com.kumaverse.kumabackend.administration.AccountStatus
import com.kumaverse.kumabackend.administration.Role
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "people")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,

    @Enumerated(EnumType.STRING)
    var accountStatus: AccountStatus,

    var name: String,


    @Column(name = "password_hash")
    var hashedPassword: String,

    @Enumerated(EnumType.STRING)
    var role: Role,

    ) : UserDetails {

    override fun getUsername(): String {
        return this.name
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {

        return listOf(GrantedAuthority { role.name })
    }

    override fun getPassword(): String? {
        return hashedPassword
    }

    override fun isAccountNonLocked(): Boolean {
        return accountStatus == AccountStatus.ACTIVE
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}