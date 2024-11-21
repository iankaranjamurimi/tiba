//// First, add these dependencies to your pom.xml if not already present
///*
//<dependencies>
//    <dependency>
//        <groupId>io.jsonwebtoken</groupId>
//        <artifactId>jjwt-api</artifactId>
//        <version>0.11.5</version>
//    </dependency>
//    <dependency>
//        <groupId>io.jsonwebtoken</groupId>
//        <artifactId>jjwt-impl</artifactId>
//        <version>0.11.5</version>
//    </dependency>
//    <dependency>
//        <groupId>io.jsonwebtoken</groupId>
//        <artifactId>jjwt-jackson</artifactId>
//        <version>0.11.5</version>
//    </dependency>
//</dependencies>
//*/
//
//// JwtConfig.java
//package com.tiba.tiba.Config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JwtConfig {
//    @Value("${jwt.secret:your-default-secret-key}")
//    private String secret;
//
//    @Value("${jwt.expiration:86400000}") // 24 hours in milliseconds
//    private Long expiration;
//
//    public String getSecret() {
//        return secret;
//    }
//
//    public Long getExpiration() {
//        return expiration;
//    }
//}
//
//// JwtService.java
//package com.tiba.tiba.Services;
//
//import com.tiba.tiba.Config.JwtConfig;
//import com.tiba.tiba.Entities.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class JwtService {
//
//    private final JwtConfig jwtConfig;
//    private final Key key;
//
//    public JwtService(JwtConfig jwtConfig) {
//        this.jwtConfig = jwtConfig;
//        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
//    }
//
//    public String generateToken(User user, String hospitalName) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", user.getId());
//        claims.put("email", user.getEmail());
//        claims.put("role", user.getRoles());
//        claims.put("hospitalName", hospitalName);
//
//        return createToken(claims, user.getEmail());
//    }
//
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
//
//// Update LoginResponse.java
//package com.tiba.tiba.DTO;
//
//import lombok.Builder;
//import lombok.Data;
//
//@Data
//@Builder
//public class LoginResponse {
//    private Long userId;
//    private String role;
//    private String hospitalName;
//    private String token;
//    private String message;
//}
//
//// Updated AuthenticationController.java
//package com.tiba.tiba.Controllers;
//
//import com.tiba.tiba.DTO.LoginRequest;
//import com.tiba.tiba.DTO.LoginResponse;
//import com.tiba.tiba.Entities.HospitalAdmin;
//import com.tiba.tiba.Entities.HospitalStaff;
//import com.tiba.tiba.Entities.User;
//import com.tiba.tiba.Repositories.HospitalAdminRepository;
//import com.tiba.tiba.Repositories.HospitalStaffRepository;
//import com.tiba.tiba.Repositories.UserSignUpRepository;
//import com.tiba.tiba.Services.JwtService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthenticationController {
//
//    private final UserSignUpRepository userSignUpRepository;
//    private final HospitalAdminRepository hospitalAdminRepository;
//    private final HospitalStaffRepository hospitalStaffRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//
//    public AuthenticationController(
//            UserSignUpRepository userSignUpRepository,
//            HospitalAdminRepository hospitalAdminRepository,
//            HospitalStaffRepository hospitalStaffRepository,
//            BCryptPasswordEncoder passwordEncoder,
//            JwtService jwtService) {
//        this.userSignUpRepository = userSignUpRepository;
//        this.hospitalAdminRepository = hospitalAdminRepository;
//        this.hospitalStaffRepository = hospitalStaffRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            // Find user by email
//            User user = userSignUpRepository.findByEmail(loginRequest.getEmail())
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            // Verify password
//            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//                return ResponseEntity.badRequest().body(
//                        LoginResponse.builder()
//                                .message("Invalid credentials")
//                                .build()
//                );
//            }
//
//            // Get hospital name based on user role
//            String hospitalName = null;
//
//            // Check if user is HospitalAdmin
//            HospitalAdmin hospitalAdmin = hospitalAdminRepository.findByUser(user);
//            if (hospitalAdmin != null) {
//                hospitalName = hospitalAdmin.getHospital().getHospitalName();
//            }
//
//            // Check if user is HospitalStaff
//            HospitalStaff hospitalStaff = hospitalStaffRepository.findByUser(user);
//            if (hospitalStaff != null) {
//                hospitalName = hospitalStaff.getHospitalName();
//            }
//
//            // Generate JWT token
//            String token = jwtService.generateToken(user, hospitalName);
//
//            // Build successful response
//            LoginResponse response = LoginResponse.builder()
//                    .userId(user.getId())
//                    .role(user.getRoles())
//                    .hospitalName(hospitalName)
//                    .token(token)
//                    .message("Login successful")
//                    .build();
//
//            return ResponseEntity.ok(response);
//
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(
//                    LoginResponse.builder()
//                            .message("Login failed: " + e.getMessage())
//                            .build()
//            );
//        }
//    }
//}
//
//// JwtAuthenticationFilter.java
//package com.tiba.tiba.Config;
//
//import com.tiba.tiba.Services.JwtService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            final String jwt = authHeader.substring(7);
//            final String userEmail = jwtService.extractAllClaims(jwt).getSubject();
//
//            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//
//                if (jwtService.validateToken(jwt)) {
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//        } catch (Exception e) {
//            // Token validation failed
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}