package com.kamegatze.learnjvm.repositories.articles.tableofcontent

import com.kamegatze.learnjvm.model.db.articles.TableOfContent
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.util.UUID

interface TableOfContentRepository : CrudRepository<TableOfContent, UUID>, PagingAndSortingRepository<TableOfContent, UUID> {

    @Query("""
        select * from table_of_contents where id in (
            select table_of_contents_id from posts_table_of_contents where posts_table_of_contents.posts_id = :postId
        )
        """)
    fun findAllByPostId(@Param("postId") postId: UUID): List<TableOfContent>

}