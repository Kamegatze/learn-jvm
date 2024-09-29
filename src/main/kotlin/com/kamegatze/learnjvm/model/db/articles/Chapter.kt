package com.kamegatze.learnjvm.model.db.articles

import java.time.Instant
import java.util.UUID

class Chapter(var id: UUID?, var label: String?, var content: String?, var order: Int?, var createdAt: Instant?, var updatedAt: Instant?) {
    constructor(label: String, content: String) : this(null, label, content, null, null, null)
    constructor() : this(null, null, null, null, null, null)
}