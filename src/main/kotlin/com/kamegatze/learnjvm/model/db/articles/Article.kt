package com.kamegatze.learnjvm.model.db.articles

import java.time.Instant
import java.util.UUID

class Article(var id: UUID?, var userId: UUID?, var label: String?, var createdAt: Instant?, var updatedAt: Instant?, var chapters: List<Chapter>?) {
    constructor(label: String, chapters: List<Chapter>) : this(null, null,  label, null, null, chapters)
    constructor() : this(null, null, null, null, null, null)
}