package com.kamegatze.learnjvm.repositories.users.impl

import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.repositories.users.UsersRepository
import com.kamegatze.learnjvm.repositories.users.row.mapper.UsersRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.*

@Repository
class UsersRepositoryImpl(private val jdbcTemplate: JdbcTemplate, private val usersRowMapper: UsersRowMapper) : UsersRepository {

    override fun findById(id: UUID): Users? {
        return jdbcTemplate.queryForObject("select * from users where id = ?", usersRowMapper, id)

    }

    override fun findAll(): Collection<Users> {
        return jdbcTemplate.query("select * from users", usersRowMapper)
    }

    override fun findAllById(ids: Collection<UUID>): Collection<Users> {
        val inSql = ids.joinToString(",") {"?"}
        val sql = "select * from users where id in ($inSql)"
        return jdbcTemplate.query(sql, usersRowMapper, *ids.toTypedArray())
    }

    override fun save(entity: Users): Users {
        val createdAt = if (entity.createdAt != null) Timestamp.from(entity.createdAt) else null
        val updatedAt = if (entity.updatedAt != null) Timestamp.from(entity.updatedAt) else null
        val lastAuthorization = if (entity.lastAuthorization != null) Timestamp.from(entity.lastAuthorization) else null

        jdbcTemplate.update(
            """insert into users (id, last_name, first_name, login, password, created_at, updated_at, last_authorization, icon_id)
            | values (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """.trimMargin(), entity.id, entity.lastName, entity.firstName, entity.login, entity.password, createdAt,
            updatedAt, lastAuthorization, entity.iconId
        )
        return findById(entity.id!!)!!
    }

    override fun update(entity: Users): Users {
        val createdAt = if (entity.createdAt != null) Timestamp.from(entity.createdAt) else null
        val updatedAt = if (entity.updatedAt != null) Timestamp.from(entity.updatedAt) else null
        val lastAuthorization = if (entity.lastAuthorization != null) Timestamp.from(entity.lastAuthorization) else null

        jdbcTemplate.update("""
            update users set last_name = ?, first_name = ?, login = ?, password = ?, created_at = ?,
            updated_at = ?, last_authorization = ?, icon_id = ? 
            where id = ?
        """.trimIndent(), entity.lastName, entity.firstName, entity.login, entity.password, createdAt,
            updatedAt, lastAuthorization, entity.iconId, entity.id)
        return findById(entity.id!!)!!
    }

    override fun delete(id: UUID) {
        jdbcTemplate.update("delete from users where id = ?", id)
    }

    override fun findByLogin(login: String): Users? {
        return jdbcTemplate.queryForObject("select * from users where login = ?", usersRowMapper, login)
    }
}