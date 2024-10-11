package com.kamegatze.learnjvm.model.db

import org.springframework.data.annotation.Id
import java.util.*

open class Entity(
    @field:Id
    var id: UUID?
) {
    constructor() : this(null)
}