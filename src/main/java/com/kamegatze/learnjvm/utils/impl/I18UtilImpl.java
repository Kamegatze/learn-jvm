package com.kamegatze.learnjvm.utils.impl;

import com.kamegatze.learnjvm.configuration.thymleaf.locale.LocaleHolder;
import com.kamegatze.learnjvm.utils.I18Util;
import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class I18UtilImpl implements I18Util {

    private final MessageSource messageSource;

    private LocaleHolder localeHolder;

    @Resource
    public void setLocaleHolder(LocaleHolder localeHolder) {
        this.localeHolder = localeHolder;
    }

    public I18UtilImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String code) {
        return getMessage(code, new String[]{});
    }

    @Override
    public String getMessage(String code, String... args) {
        return messageSource.getMessage(code, args, localeHolder.getLocale());
    }
}
