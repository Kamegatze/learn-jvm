package com.kamegatze.learnjvm.configuration.security.details

import com.kamegatze.learnjvm.model.db.users.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UsersDetails(private val user: Users, private val roleName: String) : UserDetails {

    private val authorities: MutableCollection<GrantedAuthority> = mutableListOf(SimpleGrantedAuthority(roleName))

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.login
    }
}