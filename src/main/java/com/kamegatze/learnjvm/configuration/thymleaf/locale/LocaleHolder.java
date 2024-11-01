package com.kamegatze.learnjvm.configuration.thymleaf.locale;


import java.util.Locale;


public class LocaleHolder {
    private Locale locale;

    public LocaleHolder() {
    }

    public LocaleHolder(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
