package com.kamegatze.learnjvm.model.articles

import java.util.UUID


class ArticleChapter
    (
    var id: UUID?,
    var articleId: UUID?,
    var chapterId: UUID?) {
    constructor(articleId: UUID?, chapterId: UUID?) : this(null, articleId, chapterId)
    constructor() : this(null, null)
}