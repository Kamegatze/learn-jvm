package com.kamegatze.learnjvm.model.db.icons;

import com.kamegatze.learnjvm.model.db.users.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity
@Table(name = "icon")
public class Icons extends AbstractPersistable<UUID> {
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private byte[] image;
    @OneToOne(mappedBy = "icons")
    private Users users;

    public Icons() {
    }

    public Icons(UUID id, String name, byte[] image) {
        this.setId(id);
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
