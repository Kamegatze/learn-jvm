package com.kamegatze.learnjvm.repository.roles;

import com.kamegatze.learnjvm.model.db.roles.Roles;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface RolesRepository extends CrudRepository<Roles, UUID>, PagingAndSortingRepository<Roles, UUID> {

    Optional<Roles> findByName(String name);

    @Override
    default void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteAll(@NotNull Iterable<? extends Roles> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteAllById(@NotNull Iterable<? extends UUID> uuids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void delete(@NotNull Roles entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteById(@NotNull UUID uuid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default <S extends Roles> @NotNull Iterable<S> saveAll(@NotNull Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default <S extends Roles> @NotNull S save(@NotNull S entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
