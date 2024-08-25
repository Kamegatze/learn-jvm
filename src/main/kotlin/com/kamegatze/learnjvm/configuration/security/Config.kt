package com.kamegatze.learnjvm.configuration.security


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class Config() {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)}
            .authorizeHttpRequests {
                it.requestMatchers("/posts/**", "/authentication/**", "/home/**").permitAll()
                .anyRequest().authenticated()
            }
            .formLogin { it.loginPage("/login").permitAll().defaultSuccessUrl("/home", true) }
            .logout { it.logoutSuccessUrl("/logout") }
            .sessionManagement{ it.maximumSessions(1).maxSessionsPreventsLogin(true) }
            .build()
}