package com.kamegatze.learnjvm.configuration


import com.kamegatze.learnjvm.model.db.Entity
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

@Configuration
class BeanConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun beforeConverterCallback(): BeforeConvertCallback<Entity> {
        return BeforeConvertCallback<Entity> {minion: Entity ->
            if (minion.id == null) {
                minion.id = UUID.randomUUID()
            }
            minion
        }
    }

    @Bean
    fun mutableDataSet(): MutableDataSet = MutableDataSet()

    @Bean
    fun markDownParser(options: MutableDataSet): Parser = Parser.builder(options).build()

    @Bean
    fun markDownHtmlRender(options: MutableDataSet): HtmlRenderer = HtmlRenderer.builder(options).build()

}