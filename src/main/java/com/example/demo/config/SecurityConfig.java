package com.example.demo.config;

import com.example.demo.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.demo.api.constants.Constants.PASSWORD;
import static com.example.demo.api.constants.Constants.USERNAME;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService getUserDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername(USERNAME)
                .password(PASSWORD)
                .authorities("all", "read", "write", "delete")
                .build();
        userDetailsService.createUser(user);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
                .csrf().disable()
                .cors().disable()
                .authenticationProvider(new CustomAuthenticationProvider())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
