package com.kamegatze.learnjvm.repositories.roles

import com.kamegatze.learnjvm.model.db.roles.Roles
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface RolesRepository : CrudRepository<Roles, UUID>, PagingAndSortingRepository<Roles, UUID> {
    fun findByName(name: String): Roles?

    override fun <S : Roles?> save(entity: S & Any): S & Any {
        throw UnsupportedOperationException()
    }

    override fun <S : Roles?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        throw UnsupportedOperationException()
    }

    override fun deleteById(id: UUID) {
        throw UnsupportedOperationException()
    }

    override fun delete(entity: Roles) {
        throw UnsupportedOperationException()
    }

    override fun deleteAllById(ids: MutableIterable<UUID>) {
        throw UnsupportedOperationException()
    }

    override fun deleteAll(entities: MutableIterable<Roles>) {
        throw UnsupportedOperationException()
    }

    override fun deleteAll() {
        throw UnsupportedOperationException()
    }
}