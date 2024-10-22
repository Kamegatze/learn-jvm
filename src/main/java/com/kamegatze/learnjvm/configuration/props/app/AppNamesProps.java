package com.kamegatze.learnjvm.configuration.props.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.names")
public class AppNamesProps {

    private String searchFieldName;

    public String getSearchFieldName() {
        return searchFieldName;
    }

    public void setSearchFieldName(String searchFieldName) {
        this.searchFieldName = searchFieldName;
    }
}
