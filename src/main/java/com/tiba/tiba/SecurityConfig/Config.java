package com.tiba.tiba.SecurityConfig;

import com.tiba.tiba.Services.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class Config {

    private final JwtUtil jwtUtil;

    @Autowired
    public Config(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // Changed to true as recommended
        configuration.addAllowedOriginPattern("*"); // Using pattern instead of fixed origin
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        // Swagger UI and API documentation endpoints
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/configuration/ui",
                                "/configuration/security"
                        ).permitAll()
                        // Your API endpoints
                        .requestMatchers(
                                "/api/open/**",
                                "/api/open/vitals"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new CustomAuthorizationFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }
}








//package com.tiba.tiba.SecurityConfig;
//import com.tiba.tiba.Services.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//
//@EnableWebSecurity
//@Configuration
//public class Config {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    private final JwtUtil jwtUtil;
//
//    @Autowired
//    public Config(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//    @Bean
//    public CorsConfigurationSource configurationSource(){
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(false);//should be true
//        configuration.addAllowedOrigin("*");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(c-> c.configurationSource(configurationSource()))
//                .authorizeHttpRequests(authorizeHttpRequests ->
//                        authorizeHttpRequests
//                                .requestMatchers(
//                                        "/api/open/**",
//                                        "/api/**",
//                                        "/swagger-resources",
//                                        "/swagger-resources/**",
//                                        "/configuration/ui",
//                                        "/configuration/security",
//                                        "/api-docs/**",
//                                        "/swagger-ui/**",
//                                        "/webjars/**",
//                                        "/swagger-ui.html",
//                                        "/api/open/vitals",
//                                        "/swagger-ui/**",
//                                        "/api-docs/**",
//                                        "/v3/api-docs/**",
//                                        "/v3/api-docs.yaml",
//                                        "/swagger-ui/**",
//                                        "/swagger-ui.html",
//                                        "/swagger-resources/**",
//                                        "/webjars/**",
//                                        "**/swagger-ui/**"
//
//                                ).permitAll()
//                                .anyRequest().authenticated()
//                )
//                .addFilterBefore(new CustomAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .build();
//    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(
//                "/v3/api-docs/**",
//                "/swagger-ui/**",
//                "/swagger-ui.html"
//        );
//}}
//
