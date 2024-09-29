package com.kamegatze.learnjvm.repositories.articles.chapters

import com.kamegatze.learnjvm.model.db.articles.Chapter
import com.kamegatze.learnjvm.repositories.AbstractRepository
import java.util.UUID

interface ChapterRepository : AbstractRepository<Chapter, UUID> {
    fun save(chapters: List<Chapter>): Collection<Chapter>

    fun findAllByArticleId(articleId: UUID): List<Chapter>
}