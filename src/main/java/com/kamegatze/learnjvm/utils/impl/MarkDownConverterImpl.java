package com.kamegatze.learnjvm.utils.impl;

import com.kamegatze.learnjvm.utils.MarkDownConverter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import org.springframework.stereotype.Component;

@Component
public class MarkDownConverterImpl implements MarkDownConverter {

    private final Parser parser;
    private final HtmlRenderer renderer;
    private final FlexmarkHtmlConverter flexmarkHtmlConverter;

    public MarkDownConverterImpl(
            Parser parser,
            HtmlRenderer renderer,
            FlexmarkHtmlConverter flexmarkHtmlConverter
    ) {
        this.parser = parser;
        this.renderer = renderer;
        this.flexmarkHtmlConverter = flexmarkHtmlConverter;
    }

    @Override
    public String toHtml(String markDownText) {
        final Node document = parser.parse(markDownText);
        return renderer.render(document);
    }

    @Override
    public String toMarkDown(String markDownText) {
        return flexmarkHtmlConverter.convert(markDownText);
    }
}
