package com.kamegatze.learnjvm.repositories.roles.impl

import com.kamegatze.learnjvm.model.db.roles.Roles
import com.kamegatze.learnjvm.repositories.roles.RolesRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class RolesRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : RolesRepository {
    override fun findByRole(role: String): Roles? {
        return jdbcTemplate.queryForObject("select * from roles where name = ?", rowMapper(), role)
    }

    override fun findById(id: UUID): Roles? {
        return jdbcTemplate.queryForObject("select * from roles where id = ?", rowMapper(), id)
    }

    override fun findAll(): Collection<Roles> {
        return jdbcTemplate.query("select * from roles", rowMapper())
    }

    override fun findAllById(ids: Collection<UUID>): Collection<Roles> {
        val inSql = ids.joinToString(",") {"?"}
        val sql = "select * from roles where id in ($inSql)"
        return jdbcTemplate.query(sql, rowMapper(), *ids.toTypedArray())
    }

    private fun rowMapper(): RowMapper<Roles> {
        return RowMapper<Roles> { rs, _ ->  Roles(UUID.fromString(rs.getString("id")), rs.getString("name"))}
    }
}