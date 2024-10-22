package com.kamegatze.learnjvm.utils.impl;

import com.kamegatze.learnjvm.utils.HtmlConvector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HtmlConvectorImpl implements HtmlConvector {
    @Override
    public String toFirstAttributeToText(String nameAttribute, String content) {
        Document document = Jsoup.parse(content);
        Element element = document.selectFirst(nameAttribute);
        return Objects.isNull(element) ? "" : element.text();
    }
}
