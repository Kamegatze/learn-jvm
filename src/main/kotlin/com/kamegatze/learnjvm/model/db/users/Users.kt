package com.kamegatze.learnjvm.model.db.users

import com.kamegatze.learnjvm.model.db.Entity
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table(value = "users")
data class Users(
    @field:Column(value = "last_name")
    var lastName: String,
    @field:Column(value = "first_name")
    var firstName: String,
    @field:Column(value = "login")
    var login: String,
    @field:Column(value = "password")
    var password: String,
    @field:Column(value = "created_at")
    var createdAt: Instant?,
    @field:Column(value = "updated_at")
    var updatedAt: Instant?,
    @field:Column(value = "last_authorization")
    var lastAuthorization: Instant?,
    @field:Column(value = "icon_id")
    var iconId: UUID?,
    @field:Column(value = "role_id")
    var roleId: UUID?) : Entity() {
    constructor() : this("", "", "", "", null,
        null, null, null, null)
}
