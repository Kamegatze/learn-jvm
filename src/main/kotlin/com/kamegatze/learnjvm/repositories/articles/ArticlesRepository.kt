package com.kamegatze.learnjvm.repositories.articles

import com.kamegatze.learnjvm.model.db.articles.Article
import com.kamegatze.learnjvm.repositories.AbstractRepository
import java.util.UUID

interface ArticlesRepository : AbstractRepository<Article, UUID> {

    fun findAllWithNestedChapter(): List<Article>

    fun findByIdWithNestedId(id: UUID): Article?
}