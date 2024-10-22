package com.kamegatze.learnjvm.utils.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortUtils {

    public String toStringSort(Sort sort) {
        String orderBy = sort.toString().replace(":", "");
        return orderBy.equals("UNSORTED") ? "" : orderBy;
    }

}
