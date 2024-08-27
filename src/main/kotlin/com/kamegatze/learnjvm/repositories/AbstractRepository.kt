package com.kamegatze.learnjvm.repositories

import java.util.Optional

interface AbstractRepository<T, ID> {

    fun findById(id: ID): T?

    fun findAll(): Collection<T>

    fun findAllById(ids: Collection<ID>): Collection<T>

    fun save(entity: T): T

    fun update(entity: T): T

    fun delete(id: ID)

}