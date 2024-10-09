package com.shop.shop.management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for testing purposes
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("save-product").hasRole("ADMIN")  // Only ADMIN can access this endpoint
                        .requestMatchers("/**").hasAnyRole("USER", "ADMIN")  // USER and ADMIN can access other endpoints
                        .anyRequest().authenticated()  // All other requests need to be authenticated
                )
                .formLogin(form -> form.defaultSuccessUrl("/home", true))  // Redirect to /home after login
                .httpBasic();  // Enable basic authentication for testing with Postman

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder is used to avoid encoding passwords
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
