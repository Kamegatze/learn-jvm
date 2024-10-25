package com.kamegatze.learnjvm.configuration.props.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.names")
public class AppNamesProps {

    private String searchFieldName;
    private String sortName;
    private String sizeName;
    private String pageNumberName;

    public String getPageNumberName() {
        return pageNumberName;
    }

    public void setPageNumberName(String pageNumberName) {
        this.pageNumberName = pageNumberName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSearchFieldName() {
        return searchFieldName;
    }

    public void setSearchFieldName(String searchFieldName) {
        this.searchFieldName = searchFieldName;
    }
}
