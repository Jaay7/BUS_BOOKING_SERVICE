package com.jay.staff_service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import javax.crypto.SecretKey;

public class JwtUtil {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                // .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                // validity
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String extractSubject(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }
}