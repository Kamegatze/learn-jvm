package com.kamegatze.learnjvm.configuration.security.details;

import com.kamegatze.learnjvm.model.db.users.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsersDetails implements UserDetails {
    private final Users user;
    private final String roleName;
    private final Collection<GrantedAuthority> authorities;

    public UsersDetails(Users user, String roleName) {
        this.user = user;
        this.roleName = roleName;
        authorities = List.of(new SimpleGrantedAuthority(roleName));
    }

    public Users getUser() {
        return user;
    }

    public String getRoleName() {
        return roleName;
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
