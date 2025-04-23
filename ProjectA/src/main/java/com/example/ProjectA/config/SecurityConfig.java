package com.example.ProjectA.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF cho API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").permitAll() // Cho phép các request auth
                        .requestMatchers("/role/**").permitAll()
                        .requestMatchers("/status/**").permitAll()
                        .requestMatchers("/product/**").permitAll()
                        .requestMatchers("/rolePermission/**").permitAll()
						     .requestMatchers("/warehouse-receipts/**").permitAll()
                        .requestMatchers("/permission/**").permitAll()
						 .requestMatchers("/suppliers/**").permitAll()
                        .anyRequest().authenticated()

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


