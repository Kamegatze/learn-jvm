package com.kamegatze.learnjvm.configuration.security.details

import com.kamegatze.learnjvm.repositories.users.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsersDetailsService(private val usersRepository: UsersRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val users = usersRepository.findByLogin(username) ?: throw RuntimeException("User not found")
        return UsersDetails(users)
    }
}