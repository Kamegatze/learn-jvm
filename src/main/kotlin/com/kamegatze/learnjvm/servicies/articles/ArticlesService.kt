package com.kamegatze.learnjvm.servicies.articles

import com.kamegatze.learnjvm.model.articles.Article
import com.kamegatze.learnjvm.model.users.Users
import org.springframework.data.domain.Pageable
import java.util.UUID

interface ArticlesService {

    fun getById(id: UUID): Article

    fun save(article: Article)

    fun updatePublished(published: Boolean, id: UUID)

    fun findAll(): List<Article>

    fun findAllByUser(users: Users): List<Article>

    fun findAllByUserPageable(users: Users, pageable: Pageable): List<Article>

    fun findAllPageable(pageable: Pageable): List<Article>

    fun delete(id: UUID)
}