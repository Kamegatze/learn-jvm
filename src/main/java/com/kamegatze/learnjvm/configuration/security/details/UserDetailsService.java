package com.kamegatze.learnjvm.configuration.security.details;

import com.kamegatze.learnjvm.model.db.users.Users;
import com.kamegatze.learnjvm.repository.users.UsersRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepository usersRepository;

    public UserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //todo replacing exception and change message via properties with variable language
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersByLogin = usersRepository.findByLogin(username);
        if (usersByLogin.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return new UserDetails(usersByLogin.get());
    }
}
