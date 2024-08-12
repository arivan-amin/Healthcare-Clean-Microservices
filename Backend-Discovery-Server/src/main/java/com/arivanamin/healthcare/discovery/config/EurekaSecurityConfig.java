package com.arivanamin.healthcare.discovery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class EurekaSecurityConfig {
    
    @Value ("${EUREKA_USER}")
    private String userName;
    
    @Value ("${EUREKA_PASSWORD}")
    private String password;
    
    // todo 8/12/24 - maybe change the docker compose file to run all the services to ease setup 
    //  and create another docker compose that only runs the essential services like api gateway,
    //  eureka and grafana for development
    
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((registry) -> registry.anyRequest().authenticated())
            .httpBasic(withDefaults());
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService () {
        UserDetails user =
            User.builder().username(userName).password(passwordEncoder().encode(password))
                .roles("USER").build();
        
        return new InMemoryUserDetailsManager(user);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
