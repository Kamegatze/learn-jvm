package com.kamegatze.learnjvm.model.articles;

import com.kamegatze.learnjvm.model.db.users.Users;

import java.time.LocalDate;
import java.util.UUID;

public class Article {
    private UUID id;
    private Users users;
    private String label;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean published;
    private String content;

    public Article() {
    }

    public Article(UUID id, Users users, String label, LocalDate createdAt,
                   LocalDate updatedAt, Boolean published, String content) {
        this.id = id;
        this.users = users;
        this.label = label;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.published = published;
        this.content = content;
    }

    public Article(String label, String content) {
        this.label = label;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
