package com.kamegatze.learnjvm.configuration.security.details;

import com.kamegatze.learnjvm.model.db.users.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final Users user;
    private final Collection<GrantedAuthority> authorities;

    public UserDetails(Users user) {
        this.user = user;
        authorities = List.of(new SimpleGrantedAuthority(user.getRole().getName().name()));
    }

    public Users getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }
}
