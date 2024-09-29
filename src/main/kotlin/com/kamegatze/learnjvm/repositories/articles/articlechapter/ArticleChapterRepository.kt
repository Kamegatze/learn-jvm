package com.kamegatze.learnjvm.repositories.articles.articlechapter

import com.kamegatze.learnjvm.model.db.articles.ArticleChapter
import com.kamegatze.learnjvm.repositories.AbstractRepository
import java.util.UUID

interface ArticleChapterRepository : AbstractRepository<ArticleChapter, UUID> {
    fun save(articleChapters: List<ArticleChapter>): List<ArticleChapter>
}