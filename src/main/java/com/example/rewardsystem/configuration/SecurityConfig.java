package com.example.rewardsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	
	@SuppressWarnings({ "removal", "deprecation" })
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity, especially for non-browser clients
                .authorizeRequests(requests -> requests
                        .requestMatchers("/api/customer/register", "/api/customer/login").permitAll() // Permit these endpoints
                        .anyRequest().authenticated())
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll()); // Allow logout for all

        return http.build(); // Build the SecurityFilterChain
    }

   
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Define a password encoder (BCrypt is a good choice)
	}
}
