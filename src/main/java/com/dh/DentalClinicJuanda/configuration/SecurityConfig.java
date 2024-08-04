package com.dh.DentalClinicJuanda.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter authenticationFilter; // Se encarga de validar el token

    private final AuthenticationProvider authenticationProvider; // Se encarga de autenticar al usuario

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity, not recommended for production
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/dentists", "/dentists/**").permitAll() // Allow access to /dentists endpoints
                        .requestMatchers("/patients", "/patients/**").permitAll() // Allow access to /patients endpoints
                        .requestMatchers("/..").permitAll() // Add more endpoints here
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session // Aqui se configura la politica de manejo de sesiones
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Aqui se configura la politica de manejo de sesiones
                )
                .authenticationProvider(authenticationProvider) // Sirve para autenticar al usuario
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add the JWT filter before the UsernamePasswordAuthenticationFilter
                .httpBasic(withDefaults()); // Use basic authentication

        return http.build();
    }
}