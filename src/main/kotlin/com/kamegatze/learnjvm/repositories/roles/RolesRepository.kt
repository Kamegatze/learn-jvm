package com.kamegatze.learnjvm.repositories.roles

import com.kamegatze.learnjvm.model.db.roles.Roles
import com.kamegatze.learnjvm.repositories.AbstractRepository
import java.util.UUID

interface RolesRepository : AbstractRepository<Roles, UUID> {
    fun findByRole(role: String): Roles?

    override fun save(entity: Roles): Roles {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun update(entity: Roles): Roles {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun delete(id: UUID) {
        throw UnsupportedOperationException("Not yet implemented")
    }
}