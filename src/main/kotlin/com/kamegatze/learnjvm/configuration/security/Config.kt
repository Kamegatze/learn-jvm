package com.kamegatze.learnjvm.configuration.security


import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableWebMvc
@EnableWebSecurity
class Config() {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)}
            .authorizeHttpRequests {
                it.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/posts/**", "/authentication/**", "/home/**", "/home").permitAll()
                .anyRequest().authenticated()
            }
            .formLogin { it.loginPage("/authentication/login")
                .permitAll().defaultSuccessUrl("/home").loginProcessingUrl("/authentication/processing") }
            .logout { it.logoutUrl("/authentication/logout").logoutSuccessUrl("/home").clearAuthentication(true).permitAll() }
            .build()
}