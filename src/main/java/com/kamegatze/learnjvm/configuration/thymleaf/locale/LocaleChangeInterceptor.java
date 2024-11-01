package com.kamegatze.learnjvm.configuration.thymleaf.locale;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public class LocaleChangeInterceptor extends org.springframework.web.servlet.i18n.LocaleChangeInterceptor {

    private LocaleHolder localeHolder;

    @Resource
    public void setLocaleHolder(LocaleHolder localeHolder) {
        this.localeHolder = localeHolder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException(
                    "No LocaleResolver found: not in a DispatcherServlet request?");
        }

        localeHolder.setLocale(localeResolver.resolveLocale(request));

        // Proceed in any case.
        return true;
    }

    private boolean checkHttpMethod(String currentMethod) {
        String[] configuredMethods = getHttpMethods();
        if (ObjectUtils.isEmpty(configuredMethods)) {
            return true;
        }
        for (String configuredMethod : configuredMethods) {
            if (configuredMethod.equalsIgnoreCase(currentMethod)) {
                return true;
            }
        }
        return false;
    }
}
