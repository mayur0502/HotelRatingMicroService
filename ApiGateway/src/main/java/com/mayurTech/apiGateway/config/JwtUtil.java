package com.mayurTech.apiGateway.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final String SECRET = "mysecretkeymysecretkeymysecretkey12";


    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    // Extract Username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validate Token
    public boolean validateToken(String token, String username) {
        try {
            return username.equals(extractUsername(token)) && !isExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Get Claims
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check Expiration
    private boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

}
