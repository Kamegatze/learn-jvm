package com.kamegatze.learnjvm.servicies.articles.impl

import com.kamegatze.learnjvm.model.articles.Article
import com.kamegatze.learnjvm.model.db.articles.Posts
import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.model.mappers.articles.ArticleMapper
import com.kamegatze.learnjvm.repositories.articles.posts.PostsRepository
import com.kamegatze.learnjvm.servicies.articles.ArticlesService
import com.kamegatze.learnjvm.utils.MarkDownConverter
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.multipart.MultipartFile
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*

@Service
class ArticlesServiceImpl(
    private val postsRepository: PostsRepository,
    private val articleMapper: ArticleMapper,
    private val markDownConverter: MarkDownConverter,
    private val transactionTemplate: TransactionTemplate
) : ArticlesService {


    override fun save(article: Article) {
        transactionTemplate.execute {
            article.createdAt = Instant.now()
            article.content = markDownConverter.toHtml(article.content!!)
            val post = articleMapper.articleToPosts(article)
            postsRepository.save(post)
        }
    }

    override fun save(file: MultipartFile, label: String, userId: UUID) {
        val content = String(file.bytes, StandardCharsets.UTF_8)
        val article = Article(label, content)
        article.userId = userId
        save(article)
    }

    override fun getById(id: UUID): Article {
        val post = postsRepository.findById(id).orElseThrow()
        return  articleMapper.postsToArticle(post)
    }

    override fun findAllByUserPageable(users: Users, pageable: Pageable): Page<Article> {
        val posts = postsRepository.findAllByUserId(users.id!!, pageable)
        return PageImpl(articleMapper.mapToArticles(posts.content), posts.pageable, posts.totalElements)
    }

    override fun findAllByArticlesAndUser(user: Users, searchName: String): List<Article> {
        val posts = postsRepository.findAllByUserIdAndLabelContaining(user.id!!, searchName)
        return articleMapper.mapToArticles(posts)
    }

    override fun findAllByArticlesAndUserPageable(user: Users, searchName: String, pageable: Pageable): Page<Article> {
        val posts = postsRepository.findAllByUserIdAndLabelContaining(user.id!!, searchName, pageable)
        return PageImpl(articleMapper.mapToArticles(posts.content), posts.pageable, posts.totalElements)
    }

    override fun findAllByUser(users: Users): List<Article> {
        val posts = postsRepository.findAllByUserId(users.id!!)
        return articleMapper.mapToArticles(posts)
    }

    override fun findAllPageable(pageable: Pageable): List<Article> {
        val posts = postsRepository.findAll(pageable).content
        return articleMapper.mapToArticles(posts)
    }

    @Transactional
    override fun updatePublished(published: Boolean, id: UUID) {
        postsRepository.findById(id)
            .ifPresentOrElse({post ->
                post.published = published
                postsRepository.save(post)
            }, {
                throw NoSuchElementException("Not found article")
            })
    }

    override fun findAll(): List<Article> {
        val posts = postsRepository.findAll() as MutableList<Posts>
        return articleMapper.mapToArticles(posts)
    }

    @Transactional
    override fun delete(id: UUID) {
        postsRepository.deleteById(id)
    }
}