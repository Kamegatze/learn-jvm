package com.kamegatze.learnjvm.configuration


import com.kamegatze.learnjvm.model.db.Entity
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
}