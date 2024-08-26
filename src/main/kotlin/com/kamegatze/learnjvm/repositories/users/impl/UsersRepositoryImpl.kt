package com.kamegatze.learnjvm.repositories.users.impl

import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.repositories.users.UsersRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UsersRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : UsersRepository {


    override fun findById(id: UUID): Optional<Users> {
        TODO("Not yet implemented")
    }

    override fun findAll(): Collection<Users> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: Collection<UUID>): Collection<Users> {
        TODO("Not yet implemented")
    }

    override fun save(entity: Users): Users {
        TODO("Not yet implemented")
    }

    override fun update(entity: Users): Users {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID) {
        TODO("Not yet implemented")
    }
}