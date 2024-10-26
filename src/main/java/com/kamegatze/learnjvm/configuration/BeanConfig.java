package com.kamegatze.learnjvm.configuration;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.admonition.AdmonitionExtension;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.aside.AsideExtension;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
import com.vladsch.flexmark.ext.macros.MacrosExtension;
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension;
import com.vladsch.flexmark.ext.resizable.image.ResizableImageExtension;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.SimTocExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.ext.xwiki.macros.MacroExtension;
import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLinkExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.data.MutableDataHolder;
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
    public MutableDataHolder mutableDataSet() {
        return new MutableDataSet().setFrom(ParserEmulationProfile.GITHUB_DOC)
                .set(HtmlRenderer.GENERATE_HEADER_ID, true)
                .set(AnchorLinkExtension.ANCHORLINKS_WRAP_TEXT, false)
                .set(Parser.EXTENSIONS, List.of(TablesExtension.create(), AnchorLinkExtension.create(),
                        AttributesExtension.create(), AsideExtension.create(), AbbreviationExtension.create(),
                        AdmonitionExtension.create(), DefinitionExtension.create(), EmojiExtension.create(),
                        EnumeratedReferenceExtension.create(), FootnoteExtension.create(), InsExtension.create(),
                        JekyllTagExtension.create(), MacrosExtension.create(), MediaTagsExtension.create(), ResizableImageExtension.create(),
                        SuperscriptExtension.create(), TocExtension.create(), SimTocExtension.create(), TypographicExtension.create(),
                        WikiLinkExtension.create(), MacroExtension.create(), YouTubeLinkExtension.create()));
    }

    @Bean
    public Parser markDownParser(MutableDataHolder options) {
        return Parser.builder(options).build();
    }

    @Bean
    public HtmlRenderer markDownHtmlRender(MutableDataHolder options) {
        return HtmlRenderer.builder(options).build();
    }

    @Bean
    public FlexmarkHtmlConverter flexmarkHtmlConverter(MutableDataHolder options) {return FlexmarkHtmlConverter.builder(options).build();}
}
