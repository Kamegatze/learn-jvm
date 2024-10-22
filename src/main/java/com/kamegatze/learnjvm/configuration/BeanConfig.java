package com.kamegatze.learnjvm.configuration;

import com.kamegatze.learnjvm.model.db.Entity;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public BeforeConvertCallback<Entity> beforeConvertCallback() {
        return minion -> {
            if (Objects.isNull(minion.getId())) {
                minion.setId(UUID.randomUUID());
            }
            return minion;
        };
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