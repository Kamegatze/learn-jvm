package com.kamegatze.learnjvm.model.mappers.articles

import com.kamegatze.learnjvm.configuration.MapstructConfig
import com.kamegatze.learnjvm.model.articles.Article
import com.kamegatze.learnjvm.model.db.articles.Posts
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(config = MapstructConfig::class)
interface ArticleMapper {


    fun postsToArticle(entity: Posts): Article

    fun articleToPosts(article: Article): Posts

    fun mapToPosts(articles: List<Article>) : List<Posts> {
        return articles.map { articleToPosts(it) }
    }

    fun mapToArticles(posts: List<Posts>) : List<Article> {
        return posts.map { postsToArticle(it) }
    }
}
