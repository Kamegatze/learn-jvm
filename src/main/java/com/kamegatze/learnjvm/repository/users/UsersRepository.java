package com.kamegatze.learnjvm.repository.users;

import com.kamegatze.learnjvm.model.db.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByLogin(String login);
}
