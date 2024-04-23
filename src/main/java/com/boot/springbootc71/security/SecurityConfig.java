package com.boot.springbootc71.security;

import com.boot.springbootc71.security.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(new AntPathRequestMatcher("/user", "GET")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/security/registration", "POST")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin", "POST")).hasRole("ADMIN")
                                .anyRequest().authenticated())
                .userDetailsService(customUserDetailService)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
