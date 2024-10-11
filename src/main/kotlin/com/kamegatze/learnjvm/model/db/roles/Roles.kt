package com.kamegatze.learnjvm.model.db.roles

import com.kamegatze.learnjvm.model.db.Entity
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(value = "roles")
data class Roles(
    @field:Column(value = "name")
    var name: String) : Entity() {
    constructor() : this( "")
}
