package com.kamegatze.learnjvm.model.db.articles

import com.kamegatze.learnjvm.model.db.Entity
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "posts_table_of_contents")
class PostsTableOfContents(
    @field:Column(value = "posts_id")
    var postsId: UUID,
    @field:Column(value = "table_of_contents_id")
    var tableOfContentsId: UUID,
) : Entity() {
}