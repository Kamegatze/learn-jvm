package com.kamegatze.learnjvm.repositories.articles.poststableofcontents

import com.kamegatze.learnjvm.model.db.articles.PostsTableOfContents
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface PostsTableOfContentsRepository : CrudRepository<PostsTableOfContents, UUID>,
    PagingAndSortingRepository<PostsTableOfContents, UUID> {

    @Modifying
    @Query("delete from posts_table_of_contents where posts_id = :postId")
    fun deleteByPostsId(@Param("postId") postId:UUID)

    fun findAllByPostsId(postsId: UUID): List<PostsTableOfContents>
}