package com.kamegatze.learnjvm.repositories.users.row.mapper

import com.kamegatze.learnjvm.model.db.users.Users
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.*

@Component
class UsersRowMapper: RowMapper<Users> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Users? {
        val createdAt = rs.getTimestamp("created_at").toInstant();
        val updatedAt = rs.getTimestamp("updated_at")?.toInstant();
        val lastAuthorization = rs.getTimestamp("last_authorization")?.toInstant();
        val iconId = rs.getString("icon_id")?.let { UUID.fromString(it) }
        val roleId = rs.getString("role_id").let { UUID.fromString(it) }

        return Users(UUID.fromString(rs.getString("id")), rs.getString("last_name"), rs.getString("first_name"),
            rs.getString("login"), rs.getString("password"), createdAt, updatedAt, lastAuthorization,
            iconId, roleId)
    }
}