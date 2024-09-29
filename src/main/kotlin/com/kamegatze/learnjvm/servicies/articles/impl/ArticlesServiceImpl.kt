package com.kamegatze.learnjvm.servicies.articles.impl

import com.kamegatze.learnjvm.model.db.articles.Article
import com.kamegatze.learnjvm.model.db.articles.ArticleChapter
import com.kamegatze.learnjvm.model.db.articles.Chapter
import com.kamegatze.learnjvm.repositories.articles.ArticlesRepository
import com.kamegatze.learnjvm.repositories.articles.articlechapter.ArticleChapterRepository
import com.kamegatze.learnjvm.repositories.articles.chapters.ChapterRepository
import com.kamegatze.learnjvm.servicies.articles.ArticlesService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.*

@Service
class ArticlesServiceImpl(
    private val articlesRepository: ArticlesRepository,
    private val chapterRepository: ChapterRepository,
    private val articleChapterRepository: ArticleChapterRepository
) : ArticlesService {

    @Transactional
    override fun save(article: Article) {
        article.createdAt = Instant.now()
        article.chapters?.forEachIndexed{ index, it ->
            it.order = index + 1
            it.createdAt = Instant.now()
        }
        val chapters = chapterRepository.save(article.chapters!!) as List<Chapter>
        val articleSave = articlesRepository.save(article)
        val articleChapters = mutableListOf<ArticleChapter>()
        chapters.forEach{
            articleChapters.add(ArticleChapter(articleSave.id, it.id))
        }
        articleChapterRepository.save(articleChapters)
    }

    override fun getById(id: UUID): Article {
        return articlesRepository.findByIdWithNestedId(id)!!
    }

    override fun findAll(): List<Article> {
        return articlesRepository.findAllWithNestedChapter()
    }
}