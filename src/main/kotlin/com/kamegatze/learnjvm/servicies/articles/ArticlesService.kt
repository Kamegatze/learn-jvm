package com.kamegatze.learnjvm.servicies.articles

import com.kamegatze.learnjvm.model.db.articles.Article
import java.util.UUID

interface ArticlesService {

    fun getById(id: UUID): Article

    fun save(article: Article)

    fun findAll(): List<Article>
}