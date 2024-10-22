package com.kamegatze.learnjvm.repository.articles.posts;

import com.kamegatze.learnjvm.model.db.posts.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostsRepository extends CrudRepository<Posts, UUID>, PagingAndSortingRepository<Posts, UUID> {

    List<Posts> findAllByUserId(UUID userId);

    Page<Posts> findAllByUserId(UUID userId, Pageable pageable);

    @Query("""
        select * from posts where posts.user_id = :user_id and lower(posts.label) like '%'||:label||'%'
    """)
    List<Posts> findAllByUserIdAndLabelContaining(UUID userId, String label);

    @Query("""
        select * from posts where posts.user_id = :user_id and lower(posts.label) like '%'||:label||'%'
        offset :#{#pageable.getOffset()}
        limit :#{#pageable.pageSize}
    """)
    List<Posts> findAllByUserIdAndLabelContaining(@Param("user_id") UUID userId, @Param("label")String label, Pageable pageable);

    @Query("""
        select count(*) from posts where posts.user_id = :user_id and lower(posts.label) like '%'||:label||'%'
    """)
    Long countByUserIdAndLabelContaining(@Param("user_id") UUID userId, @Param("label") String label);

}
