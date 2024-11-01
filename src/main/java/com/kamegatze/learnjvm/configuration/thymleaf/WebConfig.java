package com.kamegatze.learnjvm.configuration.thymleaf;

import com.kamegatze.learnjvm.configuration.thymleaf.locale.LocaleChangeInterceptor;
import com.kamegatze.learnjvm.configuration.thymleaf.locale.LocaleHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final ResourceBundleConfiguration resourceBundleConfiguration;

    public WebConfig(ResourceBundleConfiguration resourceBundleConfiguration) {
        this.resourceBundleConfiguration = resourceBundleConfiguration;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3900)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        var resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename(resourceBundleConfiguration.getBasename());
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(resourceBundleConfiguration.getUseCodeAsDefaultMessage());
        resourceBundleMessageSource.setDefaultLocale(Locale.of(resourceBundleConfiguration.getLocaleDefault()));
        resourceBundleMessageSource.setDefaultEncoding(resourceBundleConfiguration.getEncoding());
        resourceBundleMessageSource.setAlwaysUseMessageFormat(resourceBundleConfiguration.getAlwaysUseMessageFormat());
        return resourceBundleMessageSource;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LocaleHolder localeHolder() {
        return new LocaleHolder();
    }

}
