package com.kamegatze.learnjvm.servicies.articles;

import com.kamegatze.learnjvm.model.articles.Article;
import com.kamegatze.learnjvm.model.db.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ArticlesService {
    Article getById(UUID id);

    void save(Article article);

    void updatePublished(Boolean published, UUID id);

    List<Article> findAll();

    List<Article> findAllByUser(Users users);

    Page<Article> findAllByUserPageable(Users users, Pageable pageable);

    Page<Article> findAllPageable(Pageable pageable);

    void delete(UUID id);

    void save(MultipartFile file, String label, Users users);

    List<Article> findAllByArticlesAndUser(Users user, String searchName);

    Page<Article> findAllByArticlesAndUserPageable(Users user, String searchName, Pageable pageable);

    Article getByIdWithMarkDown(UUID id);

    void update(Article article);
}
