package com.kamegatze.learnjvm.utils;

import java.time.temporal.TemporalAccessor;

public interface DateTimeFormatterUtils {

    String dateTimeOfPattern(String pattern, TemporalAccessor localDate);

}
