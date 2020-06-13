package com.bank.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${app.jwt.secret.key}")
    private String secretKey;

    // Retrieve username from token
    public String getUsernameFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getSubject);
    }

    // Retrieve expiration date from token
    public Date getExpirationDateFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getExpiration);
    }

    // get claim (any info) from token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    // For retrieving any info from token, we need the secret key
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // check if token has expired
    public Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return this.doGenerateToken(claims, userDetails.getUsername());
    }

    // While creating token
    // 1. Define claims of token, like issuer, expiration, subject and id
    // 2. Sign the token using algorithm and secret key
    // 3. compact of jwt to url-safe-string
    public String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).
                signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    // Validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = this.getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    }
}
