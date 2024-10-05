package com.kamegatze.learnjvm.model.db.articles

import com.kamegatze.learnjvm.model.db.Entity
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(value = "table_of_contents")
class TableOfContent(
    @field:Column(value = "label")
    var label: String,
    @field:Column(value = "content")
    var content: String,
    @field:Column(value = "created_at")
    var createdAt: Instant,
    @field:Column(value = "updated_at")
    var updatedAt: Instant?,
    @field:Column(value = "orders")
    var orders: Int
) : Entity() {
}