package com.example.salon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.http.HttpMethod; 
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
            Arrays.asList("http://localhost:5173")
        );

        configuration.setAllowedMethods(
            Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        configuration.setAllowedHeaders(
            Arrays.asList("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
    .csrf(csrf -> csrf.disable())

    .cors(Customizer.withDefaults())

    .authorizeHttpRequests(auth -> auth

    // =========================
    // CUSTOMER
    // =========================

    // Registration and login are public
    .requestMatchers(HttpMethod.POST, "/customers").permitAll()
    .requestMatchers(HttpMethod.POST, "/customers/login").permitAll()

    // Only Admin can see all customers
    .requestMatchers(HttpMethod.GET, "/customers")
        .hasRole("Admin")

    // Only Admin can delete customers
    .requestMatchers(HttpMethod.DELETE, "/customers/**")
        .hasRole("Admin")


    // =========================
    // ROLES
    // =========================

    // Only Admin can manage roles
    .requestMatchers("/roles/**")
        .hasRole("Admin")


    // =========================
    // EMPLOYEES
    // =========================

    .requestMatchers(HttpMethod.GET, "/employees/**")
    .permitAll()

    .requestMatchers("/employees/**")
        .hasAnyRole("Admin", "Manager")


    // =========================
    // SERVICES
    // =========================

    // Admin + Manager
    // =========================
    // SERVICES
    // =========================

    // Anyone can view services
    .requestMatchers(HttpMethod.GET, "/services/**")
        .permitAll()

    // Only Admin + Manager can create/update/delete services
    .requestMatchers("/services/**")
        .hasAnyRole("Admin", "Manager")

    // =========================
    // EMPLOYEE-SERVICE MAPPING
    // =========================

    .requestMatchers(HttpMethod.GET, "/employee-service-mappings/**")
    .permitAll()

    .requestMatchers("/employee-service-mappings/**")
        .hasAnyRole("Admin", "Manager")


    // APPOINTMENTS
    // =========================

    // Customer can create appointment
    .requestMatchers(HttpMethod.POST, "/appointments")
        .hasRole("Customer")

    // Admin + Manager + Stylist can manage/view appointments
    .requestMatchers(HttpMethod.GET, "/appointments/**")
        .hasAnyRole("Admin", "Manager", "Stylist")

    .requestMatchers(HttpMethod.DELETE, "/appointments/**")
        .hasAnyRole("Admin", "Manager")


    // =========================
    // STATUS
    // =========================

    // Admin + Manager
    .requestMatchers("/status/**")
        .hasAnyRole("Admin", "Manager")


    // Everything else requires authentication
    .anyRequest().authenticated()
)

            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}