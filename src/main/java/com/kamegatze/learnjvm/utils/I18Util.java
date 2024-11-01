package com.kamegatze.learnjvm.utils;

public interface I18Util {
    String getMessage(String code);

    String getMessage(String code, String... args);
}
