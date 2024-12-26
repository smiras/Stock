package com.catsoftwarefactory.catstock.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authRequest) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)authRequest.requestMatchers(new String[]{"/"})).permitAll().requestMatchers(new String[]{"/css/**", "/js/**", "/images/**", "/icons/**", "/resources/**", "/templates/**"})).permitAll().requestMatchers(new String[]{"/auth/**"})).permitAll().anyRequest()).authenticated();
        }).sessionManagement((sessionManager) -> {
            sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }).authenticationProvider(this.authenticationProvider).addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    public SecurityConfig(final JWTAuthenticationFilter jwtAuthenticationFilter, final AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }
}
