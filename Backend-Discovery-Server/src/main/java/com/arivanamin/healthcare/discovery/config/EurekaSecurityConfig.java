package com.arivanamin.healthcare.discovery.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Slf4j
public class EurekaSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((registry) -> registry.anyRequest().authenticated())
            .httpBasic(withDefaults());
        return http.build();
    }
    
    @Bean
    public InMemoryUserDetailsManager userDetailsService () {
        UserDetails user =
            User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
