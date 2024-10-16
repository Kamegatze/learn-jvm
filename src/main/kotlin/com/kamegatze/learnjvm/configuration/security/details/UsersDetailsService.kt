package com.kamegatze.learnjvm.configuration.security.details

import com.kamegatze.learnjvm.repositories.roles.RolesRepository
import com.kamegatze.learnjvm.repositories.users.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsersDetailsService(private val usersRepository: UsersRepository, private val rolesRepository: RolesRepository): UserDetailsService {

    //todo replacing exception and change message via properties with variable language
    override fun loadUserByUsername(username: String): UserDetails {
        val users = usersRepository.findByLogin(username) ?: throw RuntimeException("User not found")
        val roles = rolesRepository.findById(users.roleId!!)
        return if (roles.isPresent) {
            UsersDetails(users, roles.get().name)
        } else {
            throw RuntimeException("Role not found")
        }
    }
}