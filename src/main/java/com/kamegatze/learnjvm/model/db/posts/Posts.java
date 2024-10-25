package com.kamegatze.learnjvm.model.db.posts;

import com.kamegatze.learnjvm.model.db.users.Users;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Posts extends AbstractPersistable<UUID> {
    @Column(name = "label")
    private String label;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "published")
    private Boolean published;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users users;

    public Posts() {
    }

    public Posts(UUID id, Users users, String label, Instant createdAt, Instant updatedAt,
                 Boolean published, String content) {
        this.setId(id);
        this.users = users;
        this.label = label;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.published = published;
        this.content = content;
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
