package com.example.demo.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtService {
    private final String secret = "This-is-my-secret-for-creating-jwt-token";
    long expirationTime = 60 * 60 * 1000;

    Map<String,Object> claims = new HashMap<>();

    public String generateToken(String username) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey())
                .compact();
    }

    public SecretKey getKey(){
        byte[] secretKey = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretKey);
    }

    public Claims getAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extracrUsername(String token) {
        return getAllClaims(token).getSubject();
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extracrUsername(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return getAllClaims(token).getExpiration();
    }
}
















