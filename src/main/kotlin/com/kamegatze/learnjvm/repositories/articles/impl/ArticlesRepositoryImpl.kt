package com.kamegatze.learnjvm.repositories.articles.impl

import com.kamegatze.learnjvm.model.db.articles.Article
import com.kamegatze.learnjvm.model.db.articles.Chapter
import com.kamegatze.learnjvm.repositories.articles.ArticlesRepository
import com.kamegatze.learnjvm.repositories.articles.articlechapter.ArticleChapterRepository
import com.kamegatze.learnjvm.repositories.articles.chapters.ChapterRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.*

@Repository
class ArticlesRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
    private val chapterRepository: ChapterRepository
) : ArticlesRepository {
    override fun findById(id: UUID): Article? {
        return jdbcTemplate.queryForObject("select * from posts where id = ?", rowMapper(), id)
    }

    override fun findAll(): Collection<Article> {
        return jdbcTemplate.query("select * from posts", rowMapper())
    }

    override fun findAllWithNestedChapter(): List<Article> {
        val articles = findAll() as List<Article>
        articles.forEach{
            it.chapters = chapterRepository.findAllByArticleId(it.id!!)
        }
        return articles
    }

    override fun findByIdWithNestedId(id: UUID): Article? {
        val article = findById(id)
        article?.chapters = chapterRepository.findAllByArticleId(article?.id!!)
        return article
    }

    override fun findAllById(ids: Collection<UUID>): Collection<Article> {
        val inSql = ids.joinToString(",") {"?"}
        val sql = "select * from posts where id in ($inSql)"
        return jdbcTemplate.query(sql, rowMapper(), *ids.toTypedArray())
    }

    override fun save(entity: Article): Article {
        val (createdAt, updatedAt) = getFieldTimestamp(entity)
        val id = UUID.randomUUID()

        jdbcTemplate.update("""
            insert into posts(id, user_id, label, created_at, updated_at)
            values (?, ?, ?, ?, ?)
        """.trimIndent(), id, entity.userId, entity.label, createdAt, updatedAt)
        return findById(id)!!
    }

    override fun update(entity: Article): Article {
        val (createdAt, updatedAt) = getFieldTimestamp(entity)

        jdbcTemplate.update("""
            update posts set user_id = ?, label = ?, created_at = ?, updated_at = ?
            where id = ?
        """.trimIndent(), entity.userId, entity.label, createdAt, updatedAt, entity.id)
        return findById(entity.id!!)!!
    }

    override fun delete(id: UUID) {
        jdbcTemplate.update("delete from posts where id = ?", id)
    }

    private fun rowMapper(): RowMapper<Article> {
        return RowMapper<Article> { rs, _ -> Article(UUID.fromString(rs.getString("id")), UUID.fromString(rs.getString("user_id")),
            rs.getString("label"), rs.getTimestamp("created_at")?.toInstant(), rs.getTimestamp("updated_at")?.toInstant(), listOf()
        )}
    }

    private fun getFieldTimestamp(entity: Article): List<Timestamp?> {
        val createdAt = if (entity.createdAt != null) Timestamp.from(entity.createdAt) else null
        val updatedAt = if (entity.updatedAt != null) Timestamp.from(entity.updatedAt) else null
        return listOf(createdAt, updatedAt)
    }
}