package com.kamegatze.learnjvm.configuration.props.app

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "app.names")
class AppNamesProps {
    lateinit var searchFieldName: String
}