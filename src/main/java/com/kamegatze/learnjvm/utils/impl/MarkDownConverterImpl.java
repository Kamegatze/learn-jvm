package com.kamegatze.learnjvm.utils.impl;

import com.kamegatze.learnjvm.utils.MarkDownConverter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import org.springframework.stereotype.Component;

@Component
public class MarkDownConverterImpl implements MarkDownConverter {

    private final Parser parser;
    private final HtmlRenderer renderer;


    public MarkDownConverterImpl(Parser parser, HtmlRenderer renderer) {
        this.parser = parser;
        this.renderer = renderer;
    }

    @Override
    public String toHtml(String markDownText) {
        final Node document = parser.parse(markDownText);
        return renderer.render(document);
    }
}
