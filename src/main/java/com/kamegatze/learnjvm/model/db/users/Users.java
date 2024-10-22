package com.kamegatze.learnjvm.model.db.users;

import com.kamegatze.learnjvm.model.db.Entity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table(value = "users")
public class Users extends Entity {
    @Column(value = "last_name")
    private String lastName;
    @Column(value = "first_name")
    private String firstName;
    @Column(value = "login")
    private String login;
    @Column(value = "password")
    private String password;
    @Column(value = "created_at")
    private Instant createdAt;
    @Column(value = "updated_at")
    private Instant updatedAt;
    @Column(value = "last_authorization")
    private Instant lastAuthorization;
    @Column(value = "icon_id")
    private UUID iconId;
    @Column(value = "role_id")
    private UUID roleId;

    public Users() {
    }

    public Users(UUID id, String lastName, String firstName, String login, String password, Instant createdAt, Instant updatedAt, Instant lastAuthorization, UUID iconId, UUID roleId) {
        this.setId(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastAuthorization = lastAuthorization;
        this.iconId = iconId;
        this.roleId = roleId;
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

    public UUID getIconId() {
        return iconId;
    }

    public void setIconId(UUID iconId) {
        this.iconId = iconId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }
}
