package com.kamegatze.learnjvm.model.articles;

import java.time.Instant;
import java.util.UUID;

public class Article {
    private UUID id;
    private UUID userId;
    private String label;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean published;
    private String content;

    public Article() {
    }

    public Article(UUID id, UUID userId, String label, Instant createdAt,
                   Instant updatedAt, Boolean published, String content) {
        this.id = id;
        this.userId = userId;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
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
