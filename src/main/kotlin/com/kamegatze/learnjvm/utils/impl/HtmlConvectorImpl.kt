package com.kamegatze.learnjvm.utils.impl

import com.kamegatze.learnjvm.utils.HtmlConvector
import org.jsoup.Jsoup
import org.springframework.stereotype.Component

@Component
class HtmlConvectorImpl : HtmlConvector {
    override fun toFirstAttributeToText(nameAttribute: String, content: String): String {
        val document = Jsoup.parse(content)
        return document.selectFirst(nameAttribute)?.text() ?: ""
    }
}