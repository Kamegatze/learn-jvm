package com.kamegatze.learnjvm.repositories.users

import com.kamegatze.learnjvm.model.db.users.Users
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface UsersRepository : CrudRepository<Users, UUID>, PagingAndSortingRepository<Users, UUID> {

    fun findByLogin(login: String): Users?

}