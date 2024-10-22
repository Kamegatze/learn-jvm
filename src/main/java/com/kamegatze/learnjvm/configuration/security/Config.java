package com.kamegatze.learnjvm.configuration.security;

import com.kamegatze.learnjvm.configuration.security.details.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public Config(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    private DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(it ->
                    it.requestMatchers("/posts/**", "/authentication/**", "/main/**", "/static/**", "/", "/error/**",
                                    "/logout", "/articles/view/**", "/actuator", "/actuator/*").permitAll()
                    .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .formLogin(
                        it -> it.loginPage("/authentication/login")
                        .defaultSuccessUrl("/main/home", true).loginProcessingUrl("/authentication/processing").permitAll()
                )
                .logout(it -> it.logoutUrl("/logout").logoutSuccessUrl("/authentication/login").clearAuthentication(true).permitAll())
                .build();
    }

}
