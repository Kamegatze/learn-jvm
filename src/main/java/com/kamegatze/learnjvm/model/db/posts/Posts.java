package com.kamegatze.learnjvm.model.db.posts;

import com.kamegatze.learnjvm.model.db.Entity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table(value = "posts")
public class Posts extends Entity {
    @Column(value = "user_id")
    private UUID userId;
    @Column(value = "label")
    private String label;
    @Column(value = "created_at")
    private Instant createdAt;
    @Column(value = "updated_at")
    private Instant updatedAt;
    @Column(value = "published")
    private Boolean published;
    @Column(value = "content")
    private String content;

    public Posts() {
    }

    public Posts(UUID id, UUID userId, String label, Instant createdAt, Instant updatedAt,
                 Boolean published, String content) {
        this.setId(id);
        this.userId = userId;
        this.label = label;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.published = published;
        this.content = content;
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
