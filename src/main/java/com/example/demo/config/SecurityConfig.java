package com.example.demo.config;

import com.example.demo.security.CSRFTokenFilter;
import com.example.demo.security.CustomAuthenticationProvider;
import com.example.demo.security.CustomCsrfTokenRepository;
import com.example.demo.security.LogSecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        final String[] getMatchers = {"/api/v1/**", "/actuator", "/beans", "/health", "/integrationgraph", "/liquibase", "/mappings", "/metrics", "/threaddump"};

        http
//            .csrf((csrf) -> csrf.csrfTokenRepository(new CustomCsrfTokenRepository()))
//            .csrf((csrf) -> csrf.ignoringRequestMatchers("/api/*"))
//            .cors(AbstractHttpConfigurer::disable)
            .csrf().disable()
            .cors().disable()
            .addFilterAfter(
                new LogSecurityFilter(),
                BasicAuthenticationFilter.class)
//            .addFilterAfter(
//                new CSRFTokenFilter(),
//                CsrfFilter.class)
            .authenticationProvider(customAuthenticationProvider)
            .httpBasic(Customizer.withDefaults())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/**", "/api/v1/sqs", "/actuator", "/beans", "/health", "/integrationgraph", "/liquibase", "/mappings", "/metrics", "/threaddump")
                .hasAuthority("READ")
                .requestMatchers(HttpMethod.POST, "/api/v1/**")
                .hasAnyAuthority("WRITE", "UPDATE", "DELETE");
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
