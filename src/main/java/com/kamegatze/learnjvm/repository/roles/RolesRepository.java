package com.kamegatze.learnjvm.repository.roles;

import com.kamegatze.learnjvm.model.db.roles.ERoles;
import com.kamegatze.learnjvm.model.db.roles.Roles;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolesRepository extends JpaRepository<Roles, UUID> {

    Optional<Roles> findByName(ERoles name);

    @Override
    default void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteAllByIdInBatch(@NotNull Iterable<UUID> uuids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteAllInBatch(@NotNull Iterable<Roles> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default <S extends Roles> @NotNull List<S> saveAllAndFlush(@NotNull Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default <S extends Roles> @NotNull S saveAndFlush(@NotNull S entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void flush() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default <S extends Roles> @NotNull List<S> saveAll(@NotNull Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteInBatch(@NotNull Iterable<Roles> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default <S extends Roles> @NotNull S save(@NotNull S entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteById(@NotNull UUID uuid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void delete(@NotNull Roles entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteAllById(@NotNull Iterable<? extends UUID> uuids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteAll(@NotNull Iterable<? extends Roles> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    default void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
