package com.kamegatze.learnjvm.servicies.authentication.impl

import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.repositories.roles.RolesRepository
import com.kamegatze.learnjvm.repositories.users.UsersRepository
import com.kamegatze.learnjvm.servicies.authentication.AuthenticationService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class AuthenticationServiceImpl(private val usersRepository: UsersRepository,
                                private val passwordEncoder: PasswordEncoder,
                                private val rolesRepository: RolesRepository) : AuthenticationService {

    override fun registration(user: Users): Users {
        user.id = UUID.randomUUID()
        user.createdAt = Instant.now()
        user.password = passwordEncoder.encode(user.password)
        user.roleId = rolesRepository.findByRole("ROLE_USER")?.id
        return usersRepository.save(user)
    }
}