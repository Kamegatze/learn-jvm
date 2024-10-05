package com.kamegatze.learnjvm.model.roles

import java.util.UUID

data class Roles(var id: UUID?, var name: String) {
    constructor() : this(null, "")
}
