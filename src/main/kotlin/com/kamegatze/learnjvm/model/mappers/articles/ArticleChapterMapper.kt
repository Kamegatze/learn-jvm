package com.kamegatze.learnjvm.model.mappers.articles

import com.kamegatze.learnjvm.configuration.MapstructConfig
import com.kamegatze.learnjvm.model.articles.ArticleChapter
import com.kamegatze.learnjvm.model.db.articles.PostsTableOfContents
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(config = MapstructConfig::class)
interface ArticleChapterMapper {

    @Mapping(target = "articleId", source = "entity.postsId")
    @Mapping(target = "chapterId", source = "entity.tableOfContentsId")
    fun postsTableOfContentsToArticleChapter(entity: PostsTableOfContents): ArticleChapter

    @Mapping(target = "postsId", source = "articleChapter.articleId")
    @Mapping(target = "tableOfContentsId", source = "articleChapter.chapterId")
    fun articleChapterToPostsTableOfContents(articleChapter: ArticleChapter): PostsTableOfContents


    fun mapToArticlesChapters(tableOfContent: List<PostsTableOfContents>): List<ArticleChapter> {
        return tableOfContent.map { postsTableOfContentsToArticleChapter(it) }
    }


    fun mapToTableOfContents(articleChapter: List<ArticleChapter>): List<PostsTableOfContents> {
        return articleChapter.map { articleChapterToPostsTableOfContents(it) }
    }
}