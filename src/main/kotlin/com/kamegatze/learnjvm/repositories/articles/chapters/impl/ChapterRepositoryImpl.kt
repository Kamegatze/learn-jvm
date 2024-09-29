package com.kamegatze.learnjvm.repositories.articles.chapters.impl

import com.kamegatze.learnjvm.model.db.articles.Chapter
import com.kamegatze.learnjvm.repositories.articles.chapters.ChapterRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.*

@Repository
class ChapterRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : ChapterRepository {
    override fun findById(id: UUID): Chapter? {
        return jdbcTemplate.queryForObject("select * from table_of_contents where id = ?", rowMapper() , id)
    }

    override fun findAll(): Collection<Chapter> {
        return jdbcTemplate.query("select * from table_of_contents", rowMapper())
    }

    override fun findAllById(ids: Collection<UUID>): Collection<Chapter> {
        val inSql = ids.joinToString(",") {"?"}
        val sql = "select * from table_of_contents where id in ($inSql)"
        return jdbcTemplate.query(sql, rowMapper(), *ids.toTypedArray())
    }

    override fun save(chapters: List<Chapter>): Collection<Chapter> {
        val values = chapters.joinToString(",") { "(?, ?, ?, ?, ?, ?)" }
        val sql = "insert into table_of_contents (id, label, content, created_at, updated_at, orders) values $values"

        val listParam: MutableList<Any?> = mutableListOf()
        val ids: MutableList<UUID> = mutableListOf()

        chapters.forEach{
            val id = UUID.randomUUID()
            ids.add(id)
            listParam.add(id)
            listParam.add(it.label)
            listParam.add(it.content)
            val (createdAt, updatedAt) = getFieldTimestamp(it)
            listParam.add(createdAt)
            listParam.add(updatedAt)
            listParam.add(it.order)
        }

        jdbcTemplate.update(sql,*listParam.toTypedArray())

        return findAllById(ids)
    }

    override fun findAllByArticleId(articleId: UUID): List<Chapter> {
        return jdbcTemplate.query("""
            select * from table_of_contents
            where id in (
                select table_of_contents_id from posts_table_of_contents
                where posts_table_of_contents.posts_id = ?
            )
        """.trimIndent(), rowMapper(), articleId)
    }

    override fun save(entity: Chapter): Chapter {
        val (createdAt, updatedAt) = getFieldTimestamp(entity)
        val id = UUID.randomUUID()

        jdbcTemplate.update("""
            |insert into table_of_contents (id, label, content, created_at, updated_at, orders) 
            |values(?, ?, ?, ?, ?, ?)
        """.trimMargin(), id, entity.label, entity.content, createdAt, updatedAt, entity.order)
        return findById(id)!!
    }

    override fun update(entity: Chapter): Chapter {
        val (createdAt, updatedAt) = getFieldTimestamp(entity)

        jdbcTemplate.update("""
            update table_of_contents set label = ?, content = ?, orders = ?, created_at = ?, updated_at = ?
            where id = ?
        """.trimIndent(), entity.label, entity.content, entity.order, createdAt, updatedAt, entity.id);
        return findById(entity.id!!)!!
    }

    override fun delete(id: UUID) {
        jdbcTemplate.update("delete from table_of_contents where id = ?", id)
    }

    private fun rowMapper(): RowMapper<Chapter> {
        return RowMapper<Chapter> {rs, _ -> Chapter(UUID.fromString(rs.getString("id")), rs.getString("label"),
            rs.getString("content"), rs.getInt("orders"),
            rs.getTimestamp("created_at")?.toInstant(), rs.getTimestamp("updated_at")?.toInstant())
        }
    }

    private fun getFieldTimestamp(entity: Chapter): List<Timestamp?> {
        val createdAt = if (entity.createdAt != null) Timestamp.from(entity.createdAt) else null
        val updatedAt = if (entity.updatedAt != null) Timestamp.from(entity.updatedAt) else null
        return listOf(createdAt, updatedAt)
    }
}