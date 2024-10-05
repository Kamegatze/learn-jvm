package com.kamegatze.learnjvm.repositories.users

import com.kamegatze.learnjvm.model.users.Users
import com.kamegatze.learnjvm.repositories.AbstractRepository
import java.util.UUID

interface UsersRepository : AbstractRepository<Users, UUID> {

    fun findByLogin(login: String): Users?

}