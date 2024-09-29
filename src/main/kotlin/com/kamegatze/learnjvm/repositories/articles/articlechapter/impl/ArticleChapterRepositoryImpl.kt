package com.kamegatze.learnjvm.repositories.articles.articlechapter.impl


import com.kamegatze.learnjvm.model.db.articles.ArticleChapter
import com.kamegatze.learnjvm.repositories.articles.articlechapter.ArticleChapterRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ArticleChapterRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : ArticleChapterRepository {
    override fun findById(id: UUID): ArticleChapter? {
        return jdbcTemplate.queryForObject("select * from posts_table_of_contents where id = ?", rowMapper(), id)
    }

    override fun findAll(): Collection<ArticleChapter> {
        return jdbcTemplate.query("select * from posts_table_of_contents", rowMapper())
    }

    override fun findAllById(ids: Collection<UUID>): Collection<ArticleChapter> {
        val inSql = ids.joinToString(",") {"?"}
        val sql = "select * from posts_table_of_contents where id in ($inSql)"
        return jdbcTemplate.query(sql, rowMapper(), *ids.toTypedArray())
    }

    override fun save(articleChapters: List<ArticleChapter>): List<ArticleChapter> {
        val values = articleChapters.joinToString(",") { "(?, ?, ?)" }
        val sql = "insert into posts_table_of_contents(id, posts_id, table_of_contents_id) values $values"

        val ids = mutableListOf<UUID>()
        val listParams = mutableListOf<Any?>()

        articleChapters.forEach{
            val id = UUID.randomUUID()
            ids.add(id)
            listParams.add(id)
            listParams.add(it.articleId)
            listParams.add(it.chapterId)
        }

        jdbcTemplate.update(sql, *listParams.toTypedArray())
        return findAllById(ids) as List<ArticleChapter>
    }

    override fun save(entity: ArticleChapter): ArticleChapter {
        val id = UUID.randomUUID()

        jdbcTemplate.update("""
            insert into posts_table_of_contents(id, posts_id, table_of_contents_id)
            values (?, ?, ?)
        """.trimIndent(), id, entity.articleId, entity.chapterId)
        return findById(id)!!
    }

    override fun update(entity: ArticleChapter): ArticleChapter {

        jdbcTemplate.update("""
            update posts_table_of_contents set posts_id = ?, table_of_contents_id = ? where id = ?
        """.trimIndent(), entity.articleId, entity.chapterId, entity.id)
        return findById(entity.id!!)!!
    }

    override fun delete(id: UUID) {
        jdbcTemplate.update("delete from posts_table_of_contents where id = ?", id)
    }

    private fun rowMapper(): RowMapper<ArticleChapter> {
        return RowMapper<ArticleChapter> { rs, _ -> ArticleChapter(UUID.fromString(rs.getString("id")),
                UUID.fromString(rs.getString("posts_id")), UUID.fromString(rs.getString("table_of_contents_id"))
            )
        }
    }

}