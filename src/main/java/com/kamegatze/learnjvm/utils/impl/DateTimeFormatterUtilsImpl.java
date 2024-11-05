package com.kamegatze.learnjvm.utils.impl;

import com.kamegatze.learnjvm.utils.DateTimeFormatterUtils;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

@Component
public class DateTimeFormatterUtilsImpl implements DateTimeFormatterUtils {
    @Override
    public String dateTimeOfPattern(String pattern, TemporalAccessor localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDate);
    }
}
