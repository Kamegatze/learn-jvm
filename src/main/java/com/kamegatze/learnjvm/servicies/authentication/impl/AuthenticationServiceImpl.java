package com.kamegatze.learnjvm.servicies.authentication.impl;

import com.kamegatze.learnjvm.model.db.roles.ERoles;
import com.kamegatze.learnjvm.model.db.users.Users;
import com.kamegatze.learnjvm.model.mappers.users.UsersMapper;
import com.kamegatze.learnjvm.model.registration.Registration;
import com.kamegatze.learnjvm.repository.roles.RolesRepository;
import com.kamegatze.learnjvm.repository.users.UsersRepository;
import com.kamegatze.learnjvm.servicies.authentication.AuthenticationService;
import com.kamegatze.learnjvm.servicies.authentication.exceptions.NotEqualsPasswordAndRetryPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final UsersMapper usersMapper;

    public AuthenticationServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder,
                                     RolesRepository rolesRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usersMapper = usersMapper;
    }

    @Override
    public Users registration(Registration registration) {
        if (!Objects.equals(registration.getPassword(), registration.getRetryPassword())) {
            throw new NotEqualsPasswordAndRetryPasswordException(
                    "The fields 'password' and retry 'password' not equals"
            );
        }

        final Users users = usersMapper.mapRegistrationToUser(registration);

        users.setCreatedAt(Instant.now());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        rolesRepository.findByName(ERoles.ROLE_USER).ifPresent(users::setRole);
        return usersRepository.save(users);
    }
}
