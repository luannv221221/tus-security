package com.ra.security_api.security;

import com.ra.security_api.security.jwt.CustomAccessDenied;
import com.ra.security_api.security.jwt.JwtAuthTokenFilter;
import com.ra.security_api.security.jwt.JwtEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JwtAuthTokenFilter jwtAuthTokenFilter;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Autowired
    private CustomAccessDenied customAccessDenied;
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/api/v1/carts").authenticated();
                    auth.requestMatchers("/api/v1/products").permitAll();
                    auth.requestMatchers("/api/v1/auth/**").permitAll();

                }).sessionManagement(
                        auth->
                                auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(
                        exception->
                                exception.authenticationEntryPoint(jwtEntryPoint)
                                        .accessDeniedHandler(customAccessDenied))
                .addFilterAfter(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }
}
