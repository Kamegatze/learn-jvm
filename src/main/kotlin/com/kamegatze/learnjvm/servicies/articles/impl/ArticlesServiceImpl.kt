package com.kamegatze.learnjvm.servicies.articles.impl

import com.kamegatze.learnjvm.model.articles.Article
import com.kamegatze.learnjvm.model.db.articles.Posts
import com.kamegatze.learnjvm.model.db.articles.PostsTableOfContents
import com.kamegatze.learnjvm.model.mappers.articles.ArticleChapterMapper
import com.kamegatze.learnjvm.model.mappers.articles.ArticleMapper
import com.kamegatze.learnjvm.model.mappers.articles.ChapterMapper
import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.repositories.articles.tableofcontent.TableOfContentRepository
import com.kamegatze.learnjvm.repositories.articles.posts.PostsRepository
import com.kamegatze.learnjvm.repositories.articles.poststableofcontents.PostsTableOfContentsRepository
import com.kamegatze.learnjvm.servicies.articles.ArticlesService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.*
import kotlin.NoSuchElementException

@Service
class ArticlesServiceImpl(
    private val postsRepository: PostsRepository,
    private val tableOfContentRepository: TableOfContentRepository,
    private val postsTableOfContentsRepository: PostsTableOfContentsRepository,
    private val articleChapterMapper: ArticleChapterMapper,
    private val articleMapper: ArticleMapper,
    private val chapterMapper: ChapterMapper,
) : ArticlesService {

    @Transactional
    override fun save(article: Article) {
        article.createdAt = Instant.now()
        article.chapters?.forEachIndexed{index, it ->
            it.order = index + 1
            it.createdAt = Instant.now()
        }
        val tableOfContents = chapterMapper.mapToTableOfContents(article.chapters!!)
        val post = articleMapper.articleToPosts(article)
        val tableOfContentsSave = tableOfContentRepository.saveAll(tableOfContents)
        val postSave = postsRepository.save(post)
        val postTableOfContents = mutableListOf<PostsTableOfContents>()
        tableOfContentsSave.forEach{
            postTableOfContents.add(PostsTableOfContents(postSave.id!!, it.id!!))
        }
        postsTableOfContentsRepository.saveAll(postTableOfContents)
    }

    override fun getById(id: UUID): Article {
        val post = postsRepository.findById(id).orElseThrow()
        val article = articleMapper.postsToArticle(post)
        val tableOfContents = tableOfContentRepository.findAllByPostId(id)
        article.chapters = chapterMapper.mapToChapters(tableOfContents)
        return article
    }

    override fun findAllByUserPageable(users: Users, pageable: Pageable): List<Article> {
        val posts = postsRepository.findAllByUserId(users.id!!, pageable).content
        return getArticlesWithChapters(articleMapper.mapToArticles(posts))
    }

    override fun findAllByUser(users: Users): List<Article> {
        val posts = postsRepository.findAllByUserId(users.id!!)
        return getArticlesWithChapters(articleMapper.mapToArticles(posts))
    }

    override fun findAllPageable(pageable: Pageable): List<Article> {
        val posts = postsRepository.findAll(pageable).content
        return getArticlesWithChapters(articleMapper.mapToArticles(posts))
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
        return getArticlesWithChapters(articleMapper.mapToArticles(posts))
    }

    @Transactional
    override fun delete(id: UUID) {
        val postIds = postsTableOfContentsRepository.findAllByPostsId(id).map { it.tableOfContentsId }
        postsTableOfContentsRepository.deleteByPostsId(id)
        postsRepository.deleteById(id)
        if (postIds.isNotEmpty()) {
            tableOfContentRepository.deleteAllById(postIds)
        }
    }

    private fun getArticlesWithChapters(articles: List<Article>): List<Article> {
        articles.forEach{
            it.chapters = chapterMapper.mapToChapters(tableOfContentRepository.findAllByPostId(it.id!!))
        }
        return articles
    }
}