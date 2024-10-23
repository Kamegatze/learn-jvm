package com.kamegatze.learnjvm.model.db.users;

import com.kamegatze.learnjvm.model.db.icons.Icons;
import com.kamegatze.learnjvm.model.db.posts.Posts;
import com.kamegatze.learnjvm.model.db.roles.Roles;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users extends AbstractPersistable<UUID> {
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "last_authorization")
    private Instant lastAuthorization;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Roles role;
    @OneToMany(mappedBy = "users")
    private List<Posts> posts;
    @OneToOne
    @JoinColumn(name = "icon_id", referencedColumnName = "id")
    private Icons icons;

    public Users() {
    }

    public Users(UUID id, String lastName, String firstName, String login,
                 String password, Instant createdAt, Instant updatedAt,
                 Instant lastAuthorization, Icons icons, Roles role) {
        this.setId(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastAuthorization = lastAuthorization;
        this.icons = icons;
        this.role = role;
    }

    public Icons getIcons() {
        return icons;
    }

    public void setIcons(Icons icons) {
        this.icons = icons;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Instant getLastAuthorization() {
        return lastAuthorization;
    }

    public void setLastAuthorization(Instant lastAuthorization) {
        this.lastAuthorization = lastAuthorization;
    }
}
