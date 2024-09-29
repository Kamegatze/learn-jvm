package com.kamegatze.learnjvm.model.db.articles

import java.util.UUID

class ArticleChapter(var id: UUID?, var articleId: UUID?, var chapterId: UUID?) {
    constructor(articleId: UUID?, chapterId: UUID?) : this(null, articleId, chapterId)
}