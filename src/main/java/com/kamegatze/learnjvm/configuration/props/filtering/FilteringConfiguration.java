package com.kamegatze.learnjvm.configuration.props.filtering;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "app.filtering")
public class FilteringConfiguration {
    private Map<String, List<String>> articles;

    public Map<String, List<String>> getArticles() {
        return articles;
    }

    public void setArticles(Map<String, List<String>> articles) {
        this.articles = articles;
    }
}
