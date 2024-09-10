package com.kamegatze.learnjvm.servicies.authentication.impl

import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.model.registration.Registration
import com.kamegatze.learnjvm.repositories.roles.RolesRepository
import com.kamegatze.learnjvm.repositories.users.UsersRepository
import com.kamegatze.learnjvm.servicies.authentication.AuthenticationService
import com.kamegatze.learnjvm.servicies.authentication.exceptions.NotEqualsPasswordAndRetryPasswordException
import org.modelmapper.ModelMapper

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class AuthenticationServiceImpl(private val usersRepository: UsersRepository,
                                private val passwordEncoder: PasswordEncoder,
                                private val rolesRepository: RolesRepository,
                                private val modelMapper: ModelMapper
) : AuthenticationService {

    override fun registration(registration: Registration): Users {
        if (registration.password != registration.retryPassword) {
            throw NotEqualsPasswordAndRetryPasswordException("The fields 'password' and retry 'password' not equals")
        }

        val users = modelMapper.map(registration, Users::class.java)


        users.id = UUID.randomUUID()
        users.createdAt = Instant.now()
        users.password = passwordEncoder.encode(users.password)
        users.roleId = rolesRepository.findByRole("ROLE_USER")?.id
        return usersRepository.save(users)
    }
}