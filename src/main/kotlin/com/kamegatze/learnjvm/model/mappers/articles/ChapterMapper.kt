package com.kamegatze.learnjvm.model.mappers.articles

import com.kamegatze.learnjvm.configuration.MapstructConfig
import com.kamegatze.learnjvm.model.articles.Chapter
import com.kamegatze.learnjvm.model.db.articles.TableOfContent
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(config = MapstructConfig::class)
interface ChapterMapper {
    @Mapping(target = "order", source = "entity.orders")
    fun tableOfContentToChapter(entity: TableOfContent): Chapter

    @Mapping(target = "orders", source = "chapter.order")
    fun chapterToTableOfContent(chapter: Chapter): TableOfContent

    fun mapToTableOfContents(chapters: List<Chapter>): List<TableOfContent> {
        return chapters.map { chapterToTableOfContent(it) }
    }

    fun mapToChapters(tableOfContents: List<TableOfContent>): List<Chapter> {
        return tableOfContents.map { tableOfContentToChapter(it) }
    }
}