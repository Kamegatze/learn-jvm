package com.kamegatze.learnjvm.repositories.articles.posts

import com.kamegatze.learnjvm.model.db.articles.Posts
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface PostsRepository : CrudRepository<Posts, UUID>, PagingAndSortingRepository<Posts, UUID> {

    fun findAllByUserId(userId: UUID): List<Posts>

    fun findAllByUserId(userId: UUID, pageable: Pageable): Page<Posts>

    fun findAllByUserIdAndLabelContaining(userId: UUID, label: String): List<Posts>

    fun findAllByUserIdAndLabelContaining(userId: UUID, label: String, pageable: Pageable): Page<Posts>
}