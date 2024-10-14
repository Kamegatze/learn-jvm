package com.kamegatze.learnjvm.model.articles

import java.time.Instant
import java.util.*

class Article(
    var id: UUID?,
    var userId: UUID?,
    var label: String?,
    var createdAt: Instant?,
    var updatedAt: Instant?,
    var published: Boolean?,
    var content: String?
) {
    constructor(label: String, content: String) : this(null, null,  label, null, null, null, content)
    constructor() : this(null, null, null, null, null, null, null)
}