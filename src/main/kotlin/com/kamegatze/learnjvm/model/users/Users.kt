package com.kamegatze.learnjvm.model.users

import java.time.Instant
import java.util.UUID

data class Users(var id: UUID?, var lastName: String, var firstName: String,
                 var login: String, var password: String, var createdAt: Instant?,
                 var updatedAt: Instant?, var lastAuthorization: Instant?, var iconId: UUID?, var roleId: UUID?) {
    constructor() : this(null, "", "", "", "", null,
        null, null, null, null)
}
