package com.kamegatze.learnjvm.configuration;

import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public MutableDataSet mutableDataSet() {
        return new MutableDataSet()
                .set(HtmlRenderer.GENERATE_HEADER_ID, true)
                .set(AnchorLinkExtension.ANCHORLINKS_WRAP_TEXT, false)
                .set(Parser.EXTENSIONS, List.of(TablesExtension.create(), AnchorLinkExtension.create()));
    }

    @Bean
    public Parser markDownParser(MutableDataSet options) {
        return Parser.builder(options).build();
    }

    @Bean
    public HtmlRenderer markDownHtmlRender(MutableDataSet options) {
        return HtmlRenderer.builder(options).build();
    }

}
