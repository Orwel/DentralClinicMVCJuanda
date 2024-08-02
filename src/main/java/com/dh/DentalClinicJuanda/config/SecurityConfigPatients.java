package com.dh.DentalClinicJuanda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfigPatients {
    // src/main/java/com/dh/DentalClinicJuanda/config/SecurityConfig.java


    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
    public class SecurityConfigDentists {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity, not recommended for production
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/patients/**").permitAll() // Allow access to /dentists endpoints
                            .anyRequest().authenticated()
                    )
                    .httpBasic(withDefaults()); // Use basic authentication

            return http.build();
        }
    }
}
