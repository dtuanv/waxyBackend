package com.waxy.utils;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class.getSimpleName());
    private static String JWT_SECRET_KEY;

    @Autowired
    public JwtUtil(EnvUtil envUtil) {
        this.JWT_SECRET_KEY = envUtil.getEnv("JWT_SECRET_KEY");

    }

//    private static String JWT_SECRET_KEY = System.getenv("JWT_SECRET_KEY");
    private final static int JWT_EXPIRATION_IN_MS = 3600000; // (1 hour = 3600000 ms)

    public static String generateJwtToken(String userEmail) {

        Instant today = Instant.now();
        Instant expiration = today.plus(JWT_EXPIRATION_IN_MS, ChronoUnit.MILLIS);
        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(Date.from(today))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY.getBytes())
                .compact();
    }

    public static String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY.getBytes())
                .parseClaimsJws(token).getBody().getSubject();
    }

    public static boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET_KEY.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }


    public void setJwtSecretKey(String jwtSecretKey) {
        this.JWT_SECRET_KEY = jwtSecretKey;
    }
}