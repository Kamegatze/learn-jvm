package com.kamegatze.learnjvm.utils.impl

import com.kamegatze.learnjvm.utils.MarkDownConverter
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import org.springframework.stereotype.Component

@Component
class MarkDownConverterImpl(private val parser: Parser, private val renderer: HtmlRenderer) : MarkDownConverter {

    override fun toHtml(markDownText: String): String {
        val document: Node = parser.parse(markDownText)
        val outputHtml: String = renderer.render(document)
        return outputHtml
    }
}