package com.kamegatze.learnjvm.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class Config(private val passwordEncoder: PasswordEncoder, private val userDetailsService: UserDetailsService) {

    private fun authenticationProvider(): DaoAuthenticationProvider = DaoAuthenticationProvider(passwordEncoder)
        .apply { setUserDetailsService(userDetailsService) }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)}
            .authorizeHttpRequests {
                it.requestMatchers("/posts/**", "/authentication/**", "/home/**", "/home").permitAll()
                .anyRequest().authenticated()
            }
            .authenticationProvider(authenticationProvider())
            .formLogin { it.loginPage("/authentication/login")
                .permitAll().defaultSuccessUrl("/home").loginProcessingUrl("/authentication/processing") }
            .logout { it.logoutUrl("/authentication/logout").logoutSuccessUrl("/home").clearAuthentication(true).permitAll() }
            .build()
}