package com.kamegatze.learnjvm.utils.impl;

import com.kamegatze.learnjvm.configuration.props.filtering.FilteringConfiguration;
import com.kamegatze.learnjvm.model.filtering.Filter;
import com.kamegatze.learnjvm.utils.Filtering;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FilteringImpl implements Filtering {

    private final FilteringConfiguration filteringConfiguration;

    public FilteringImpl(FilteringConfiguration filteringConfiguration) {
        this.filteringConfiguration = filteringConfiguration;
    }

    @Override
    public List<Filter> processing(final Sort sort, final List<String> propertiesPass) {
        List<Filter> filters = getActualFilterWithLocale(propertiesPass);
        sort.stream().forEach(it ->
            filters.stream().filter(item -> Objects.equals(item.getProperty(), it.getProperty()))
                .findAny().ifPresent(filter -> {
                    filter.setDirection(it.getDirection());
                    filter.setValue(String.format("%s,%s", it.getProperty(), filter.getDirection()));
                })
        );

        return filters;
    }

    private List<Filter> getActualFilterWithLocale(List<String> propertiesPass) {
        filteringConfiguration.uploadArticles();
        return filteringConfiguration.getArticles().entrySet().stream()
                .filter(it -> !propertiesPass.contains(it.getKey()))
                .map(
                it -> new Filter((Sort.Direction) null, it.getKey(), it.getValue().getFirst(),
                        it.getValue().get(1), it.getValue().getLast())
        ).toList();
    }
}
