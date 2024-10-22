package com.kamegatze.learnjvm.utils.impl;

import com.kamegatze.learnjvm.model.generation.url.Parameters;
import com.kamegatze.learnjvm.utils.GenerationUrlPage;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GenerationUrlPageImpl implements GenerationUrlPage {
    private final String PAGE_NAME_PARAM = "page";
    private final String SIZE_NAME_PARAM = "size";
    private final String SORT_NAME_PARAM = "sort";

    @Override
    public List<String> generation(final Parameters parameters) {
        final String url = parameters.getUrl();
        String queryStaticParams = queryJoin("&", parameters.getQueryStaticParams());
        final String sort = sortToQueryParam(parameters.getSort());
        queryStaticParams = getQueryStaticParams(queryStaticParams, sort);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < parameters.getCountPage(); i++) {
            final String urlPage = getUrlPage(url, i, parameters, queryStaticParams);
            result.add(urlPage);
        }
        return result;
    }

    private String sortToQueryParam(Sort sort) {
        return sort.stream().map(it -> String.format("%s=%s,%s", SORT_NAME_PARAM, it.getProperty(), it.getDirection().name().toLowerCase()))
                .collect(Collectors.joining("&"));
    }

    private String getQueryStaticParams(String queryStaticParams, String sort) {
        if (queryStaticParams.isEmpty() && sort.isEmpty()) {
            return "";
        }
        if (sort.isEmpty()) {
            return queryStaticParams;
        }
        if (queryStaticParams.isEmpty()) {
            return sort;
        }
        return String.format("%s&%s", queryStaticParams, sort);
    }

    private String getUrlPage(String url, int index, Parameters parameters, String queryStaticParams) {
        if (Objects.isNull(queryStaticParams) || queryStaticParams.isEmpty() || queryStaticParams.isBlank()) {
            return String.format("%s?%s=%s&%s=%s", url, PAGE_NAME_PARAM, index, SIZE_NAME_PARAM, parameters.getPageSize());
        }
        return String.format("%s?%s=%s&%s=%s&%s", url, PAGE_NAME_PARAM, index, SIZE_NAME_PARAM, parameters.getPageSize(), queryStaticParams);
    }

    private String queryJoin(final String delimiter, final String[] queryStaticParams) {
        if (queryStaticParams.length % 2 != 0) {
            throw new RuntimeException("queryStaticParams must contain an even number of parameters");
        }
        String queryStaticJoin = "";
        for (int i = 0; i < queryStaticParams.length; i += 2) {
            if (Objects.isNull(queryStaticParams[i]) || Objects.isNull(queryStaticParams[i + 1]) || queryStaticParams[i].isEmpty() || queryStaticParams[i + 1].isEmpty()) {
                continue;
            }
            if (i == 0) {
                queryStaticJoin = String.format("%s=%s", queryStaticParams[i], queryStaticParams[i + 1]);
            } else {
                queryStaticJoin = String.format("%s%s%s=%s", queryStaticJoin, delimiter, queryStaticParams[i], queryStaticParams[i + 1]);
            }
        }
        return queryStaticJoin;
    }
}
