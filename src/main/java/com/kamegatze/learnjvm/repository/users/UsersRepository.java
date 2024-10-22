package com.kamegatze.learnjvm.repository.users;

import com.kamegatze.learnjvm.model.db.users.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends CrudRepository<Users, UUID>, PagingAndSortingRepository<Users, UUID> {
    Optional<Users> findByLogin(String login);
}
