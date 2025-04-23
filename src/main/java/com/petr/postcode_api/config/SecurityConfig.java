package com.petr.postcode_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.petr.postcode_api.user.SecurityUser;
import com.petr.postcode_api.user.UserRepository;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    DataSource dataSource;
    private AuthEntryPointJwt unauthorizedHandler;

    public SecurityConfig(DataSource dataSource, AuthEntryPointJwt unauthorizedHandler) {
        this.dataSource = dataSource;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            // Public endpoints
            .requestMatchers("/signin").permitAll()
            .requestMatchers(HttpMethod.GET, "/postcodes/**").permitAll()
            // Admin-only endpoint
            .requestMatchers("/users/**").hasRole("ADMIN")
            // Protected
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
        .csrf(csrf -> csrf.disable())
        .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository
            .findByUsername(username)
            .map(SecurityUser::new) //custom UserDetails wrapper
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}