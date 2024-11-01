package com.kamegatze.learnjvm.configuration.thymleaf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.messages")
public class ResourceBundleConfiguration {
    private String basename;
    private Boolean useCodeAsDefaultMessage;
    private String encoding;
    private Boolean alwaysUseMessageFormat;
    private String localeDefault;

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public Boolean getUseCodeAsDefaultMessage() {
        return useCodeAsDefaultMessage;
    }

    public void setUseCodeAsDefaultMessage(Boolean useCodeAsDefaultMessage) {
        this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Boolean getAlwaysUseMessageFormat() {
        return alwaysUseMessageFormat;
    }

    public void setAlwaysUseMessageFormat(Boolean alwaysUseMessageFormat) {
        this.alwaysUseMessageFormat = alwaysUseMessageFormat;
    }

    public String getLocaleDefault() {
        return localeDefault;
    }

    public void setLocaleDefault(String localeDefault) {
        this.localeDefault = localeDefault;
    }
}
