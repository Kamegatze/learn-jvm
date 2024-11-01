package com.kamegatze.learnjvm.configuration.props.filtering;

import com.kamegatze.learnjvm.utils.I18Util;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FilteringConfiguration {
    private Map<String, List<String>> articles;
    private final I18Util i18Util;

    public FilteringConfiguration(I18Util i18Util) {
        this.i18Util = i18Util;
    }

    public void uploadArticles() {
        String published = i18Util.getMessage("app.filtering.articles.published");
        String label = i18Util.getMessage("app.filtering.articles.label");
        if (articles == null) {
            articles = new HashMap<>();
        }
        if (published != null) {
            List<String> publishedList = Arrays.stream(published.split(","))
                    .map(String::trim)
                    .toList();
            articles.put("published", publishedList);
        }
        if (label != null) {
            List<String> labelList = Arrays.stream(label.split(","))
                    .map(String::trim)
                    .toList();
            articles.put("label", labelList);
        }
    }

    public Map<String, List<String>> getArticles() {
        return articles;
    }

    public void setArticles(Map<String, List<String>> articles) {
        this.articles = articles;
    }
}
