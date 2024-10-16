package com.kamegatze.learnjvm.servicies.articles

import com.kamegatze.learnjvm.model.articles.Article
import com.kamegatze.learnjvm.model.db.users.Users
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

interface ArticlesService {

    fun getById(id: UUID): Article

    fun save(article: Article)

    fun updatePublished(published: Boolean, id: UUID)

    fun findAll(): List<Article>

    fun findAllByUser(users: Users): List<Article>

    fun findAllByUserPageable(users: Users, pageable: Pageable): Page<Article>

    fun findAllPageable(pageable: Pageable): List<Article>

    fun delete(id: UUID)

    fun save(file: MultipartFile, label: String, userId: UUID)

    fun findAllByArticlesAndUser(user: Users, searchName: String): List<Article>

    fun findAllByArticlesAndUserPageable(user: Users, searchName: String, pageable: Pageable): Page<Article>
}