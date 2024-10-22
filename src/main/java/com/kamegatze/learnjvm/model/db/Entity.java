package com.kamegatze.learnjvm.model.db;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public abstract class Entity {
    @Id
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
