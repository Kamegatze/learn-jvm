package com.kamegatze.learnjvm.servicies.articles.impl;

import com.kamegatze.learnjvm.model.articles.Article;
import com.kamegatze.learnjvm.model.db.posts.Posts;
import com.kamegatze.learnjvm.model.db.users.Users;
import com.kamegatze.learnjvm.model.mappers.articles.ArticleMapper;
import com.kamegatze.learnjvm.repository.articles.posts.PostsRepository;
import com.kamegatze.learnjvm.servicies.articles.ArticlesService;
import com.kamegatze.learnjvm.utils.MarkDownConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    private final PostsRepository postsRepository;
    private final ArticleMapper articleMapper;
    private final MarkDownConverter markDownConverter;
    private final TransactionTemplate transactionTemplate;

    public ArticlesServiceImpl(PostsRepository postsRepository, ArticleMapper articleMapper, MarkDownConverter markDownConverter,
                               TransactionTemplate transactionTemplate) {
        this.postsRepository = postsRepository;
        this.articleMapper = articleMapper;
        this.markDownConverter = markDownConverter;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Article getById(UUID id) {
        final Posts post = postsRepository.findById(id).orElseThrow();
        return articleMapper.postsToArticle(post);
    }

    @Override
    public void save(Article article) {
        transactionTemplate.execute ((status) -> {
            article.setCreatedAt(Instant.now());
            article.setContent(markDownConverter.toHtml(article.getContent()));
            final Posts post = articleMapper.articleToPosts(article);
            return postsRepository.save(post);
        });
    }

    @Override
    @Transactional
    public void updatePublished(Boolean published, UUID id) {
        postsRepository.findById(id)
            .ifPresentOrElse(posts -> {
                posts.setPublished(published);
                postsRepository.save(posts);
            }, () -> {
                throw new NoSuchElementException("Not found article");
            });
    }

    @Override
    public List<Article> findAll() {
        final List<Posts> posts = postsRepository.findAll();
        return articleMapper.mapToArticles(posts);
    }

    @Override
    public List<Article> findAllByUser(Users users) {
        final List<Posts> posts = postsRepository.findAllByUsers(users);
        return articleMapper.mapToArticles(posts);
    }

    @Override
    public Page<Article> findAllByUserPageable(Users users, Pageable pageable) {
        final Page<Posts> posts = postsRepository.findAllByUsers(users, pageable);
        return posts.map(articleMapper::postsToArticle);
    }

    @Override
    public Page<Article> findAllPageable(Pageable pageable) {
        final Page<Posts> posts = postsRepository.findAll(pageable);
        return posts.map(articleMapper::postsToArticle);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        postsRepository.deleteById(id);
    }

    @Override
    public void save(MultipartFile file, String label, Users users) {
        final String content;
        try {
            content = new String(file.getBytes(), StandardCharsets.UTF_8);
            final Article article = new Article(label, content);
            article.setUsers(users);
            save(article);
        } catch (IOException e) {
            throw new RuntimeException("Error read file");
        }
    }

    @Override
    public List<Article> findAllByArticlesAndUser(Users user, String searchName) {
        final List<Posts> posts = postsRepository.findAllByUsersAndLabelContaining(user, searchName);
        return articleMapper.mapToArticles(posts);
    }

    @Override
    public Page<Article> findAllByArticlesAndUserPageable(Users user, String searchName, Pageable pageable) {
        final Page<Posts> posts = postsRepository.findAllByUsersAndLabelContaining(user, searchName, pageable);
        return posts.map(articleMapper::postsToArticle);
    }
}
