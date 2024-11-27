package com.tiba.tiba.SecurityConfig;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tiba.tiba.Services.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.*;
@EnableWebSecurity
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private static final Logger log = LoggerFactory.getLogger(CustomAuthorizationFilter.class);
    private final JsonMapper jsonMapper = new JsonMapper();

    public CustomAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // list of allowed paths
        List<String> pathsAllowed = Arrays.asList("/api/open/**", "/api/", "/swagger-ui/**", "/api-docs/**","**/swagger-ui/**","/api/auth/**","/api/auth/otp/**","/api/**");

        if (isAllowedPath(pathsAllowed, request.getServletPath()) ||
                request.getServletPath().contains("docs") ||
                request.getServletPath().contains("/swagger-ui")) {
            log.info("Allowed path {} to go through", request.getServletPath());
            filterChain.doFilter(request, response);
        } else {
            String authToken = request.getHeader("Authorization");
            log.info("Gotten auth token: {}", authToken);

            if (authToken == null || !authToken.startsWith("Bearer ")) {
                log.warn("Invalid token format or no token provided");
                sendErrorResponse(response, "Invalid token format or no token provided");
            } else {
                authToken = authToken.substring("Bearer ".length());
                log.info("Extracted token: {}", authToken);

                try {
                    String username = jwtUtil.getEmailFromToken(authToken);
                    log.info("Username extracted from token: {}", username);

                    if (!jwtUtil.validateToken(authToken)) {
                        log.warn("Invalid token for username: {}", username);
                        sendErrorResponse(response, "Invalid token");
                        return;
                    }

                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (ExpiredJwtException e) {
                    log.error("Token has expired: {}", e.getMessage());
                    sendErrorResponse(response, "Token has expired");
                } catch (MalformedJwtException e) {
                    log.error("Invalid token: {}", e.getMessage());
                    sendErrorResponse(response, "Invalid token");
                } catch (Exception e) {
                    log.error("Error during token validation: {}", e.getMessage());
                    sendErrorResponse(response, "Token validation error");
                }
            }
        }
    }

    private boolean isAllowedPath(Collection<String> pathsAllowed, String servletPath) {
        return pathsAllowed.stream().anyMatch(servletPath::startsWith);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        resMap.put("message", message);

        jsonMapper.writeValue(response.getOutputStream(), resMap);
    }
}
