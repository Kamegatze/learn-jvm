package com.kamegatze.learnjvm.repository.articles.posts;

import com.kamegatze.learnjvm.model.db.posts.Posts;
import com.kamegatze.learnjvm.model.db.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostsRepository extends JpaRepository<Posts, UUID> {

    List<Posts> findAllByUsers(Users users);

    Page<Posts> findAllByUsers(Users users, Pageable pageable);

    @Query("""
        select posts from Posts posts where posts.users.id = :#{#user.id} and lower(posts.label) like lower('%'||:label||'%')
    """)
    List<Posts> findAllByUsersAndLabelContaining(@Param("user") Users users, String label);

    @Query("""
        select posts from Posts posts where posts.users.id = :#{#user.id} and lower(posts.label) like lower('%'||:label||'%')
    """)
    Page<Posts> findAllByUsersAndLabelContaining(@Param("user") Users users, @Param("label") String label, Pageable pageable);
}
