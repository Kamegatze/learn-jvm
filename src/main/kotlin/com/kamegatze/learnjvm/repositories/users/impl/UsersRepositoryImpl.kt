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
        val isNotExists = findById(entity.id) == null
        if (isNotExists) {
            jdbcTemplate.update(
                """insert into users (id, last_name, first_name, login, password, created_at, updated_at, last_authorization, icon_id)
                | values (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """.trimMargin(), entity.id, entity.lastName, entity.firstName, entity.login, entity.password, Timestamp.from(entity.createdAt),
                Timestamp.from(entity.updatedAt), Timestamp.from(entity.lastAuthorization), entity.iconId
            )
            return findById(entity.id)!!
        }
        throw IllegalArgumentException("User with id ${entity.id} already exists")
    }

    override fun update(entity: Users): Users {
        val isExists = findById(entity.id) != null
        if (isExists) {
            jdbcTemplate.update("""
                update users set last_name = ?, first_name = ?, login = ?, password = ?, created_at = ?,
                updated_at = ?, last_authorization = ?, icon_id = ? 
                where id = ?
            """.trimIndent(), entity.lastName, entity.firstName, entity.login, entity.password, Timestamp.from(entity.createdAt),
                Timestamp.from(entity.updatedAt), Timestamp.from(entity.lastAuthorization), entity.iconId, entity.id)
            return findById(entity.id)!!
        }
        throw IllegalArgumentException("User with id ${entity.id} not exists")
    }

    override fun delete(id: UUID) {
        jdbcTemplate.update("""delete from users where id = ?""", id)
    }

    override fun findByLogin(login: String): Users? {
        return jdbcTemplate.queryForObject("select * from users where login = ?", usersRowMapper, login)
    }
}