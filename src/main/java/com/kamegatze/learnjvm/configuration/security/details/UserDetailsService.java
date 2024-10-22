package com.kamegatze.learnjvm.configuration.security.details;

import com.kamegatze.learnjvm.model.db.roles.Roles;
import com.kamegatze.learnjvm.model.db.users.Users;
import com.kamegatze.learnjvm.repository.roles.RolesRepository;
import com.kamegatze.learnjvm.repository.users.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    public UserDetailsService(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    //todo replacing exception and change message via properties with variable language
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersByLogin = usersRepository.findByLogin(username);
        if (usersByLogin.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Optional<Roles> rolesById = rolesRepository.findById(usersByLogin.get().getRoleId());
        if (rolesById.isEmpty()) {
            throw new RuntimeException("Roles not found by id");
        }
        return new UsersDetails(usersByLogin.get(), rolesById.get().getName());
    }
}
