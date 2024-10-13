package com.scentheartsstudio.scentheartsstudio.configs;

import com.scentheartsstudio.scentheartsstudio.jwt.AuthEntryPointJwt;
import com.scentheartsstudio.scentheartsstudio.jwt.AuthTokenFilter;
import com.scentheartsstudio.scentheartsstudio.jwt.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public AuthTokenFilter authTokenFilter(){
        return new AuthTokenFilter();
    }

    //
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
            // Endpoint yang tidak memerlukan otorisasi
            .requestMatchers("/api/category", "/api/category/image", "/api/product").permitAll()
                .requestMatchers("/category/pss","/api/category/insert", "/api/category/update", "/api/category/delete",
                 "/api/product/pss", "/api/product/insert", "/api/product/update", "/api/product/delete",
                 "/api/productSize/**", "/api/cart/**", "/api/profile/**").authenticated()
                // others endpoints that don't need authorization
                .anyRequest().permitAll())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJwt))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(jwtUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
