package com.kamegatze.learnjvm.model.mappers.articles;

import com.kamegatze.learnjvm.configuration.MapstructConfig;
import com.kamegatze.learnjvm.model.articles.Article;
import com.kamegatze.learnjvm.model.db.posts.Posts;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface ArticleMapper {
    Article postsToArticle(Posts entity);

    Posts articleToPosts(Article article);

    default List<Posts> mapToPosts(List<Article> articles) {
        return articles.stream().map(this::articleToPosts).toList();
    }

    default List<Article> mapToArticles(List<Posts> posts) {
        return posts.stream().map (this::postsToArticle).toList();
    }
}
