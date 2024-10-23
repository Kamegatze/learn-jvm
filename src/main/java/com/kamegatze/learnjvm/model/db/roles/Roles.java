package com.kamegatze.learnjvm.model.db.roles;

import com.kamegatze.learnjvm.model.db.users.Users;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Roles extends AbstractPersistable<UUID> {
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ERoles name;

    @OneToMany(mappedBy = "role")
    private List<Users> users;

    public Roles() {
    }

    public Roles(UUID id, ERoles name) {
        this.setId(id);
        this.name = name;
    }

    public ERoles getName() {
        return name;
    }

    public void setName(ERoles name) {
        this.name = name;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
