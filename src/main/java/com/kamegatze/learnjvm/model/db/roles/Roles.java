package com.kamegatze.learnjvm.model.db.roles;

import com.kamegatze.learnjvm.model.db.Entity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(value = "roles")
public class Roles extends Entity {
    @Column(value = "name")
    private String name;

    public Roles() {
    }

    public Roles(UUID id, String name) {
        this.setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
