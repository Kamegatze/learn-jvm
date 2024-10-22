package com.kamegatze.learnjvm.model.generation.url;

import org.springframework.data.domain.Sort;

public class Parameters {
    private String url;
    private int countPage;
    private int pageSize;
    private String[] queryStaticParams;
    private Sort sort;

    private Parameters() {
    }

    public static synchronized Parameters build() {
        return new Parameters();
    }

    public Parameters url(String url) {
        this.url = url;
        return this;
    }

    public Parameters countPage(int countPage) {
        this.countPage = countPage;
        return this;
    }

    public Parameters pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Parameters queryStaticParams(String[] queryParams) {
        this.queryStaticParams = queryParams;
        return this;
    }

    public Parameters sort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public Sort getSort() {
        return sort;
    }

    public String getUrl() {
        return url;
    }

    public int getCountPage() {
        return countPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String[] getQueryStaticParams() {
        return queryStaticParams;
    }
}
