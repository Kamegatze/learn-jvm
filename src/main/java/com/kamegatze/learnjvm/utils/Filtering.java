package com.kamegatze.learnjvm.utils;

import com.kamegatze.learnjvm.model.filtering.Filter;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface Filtering {
    List<Filter> processing(final Sort sort, final List<String> propertiesPass);
}
