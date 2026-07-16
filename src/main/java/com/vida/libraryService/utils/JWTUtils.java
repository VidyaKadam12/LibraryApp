package com.vida.libraryService.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {

    private String SECRET_KEY = "oXRc&Y2V<YK()dCNpEkjGQz>xj=B@l9k2^vddzZbI}A";

    private SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String userName){
        Map<String, Object> claims = new HashMap<>();
        return createToken(userName, claims);
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    public String extractClaim(String token, String claim){
        return extractAllClaims(token).get(claim, String.class);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }


    private String createToken(String subject, Map<String, Object> claims){
        String jwtToken = Jwts.builder()
                .claims(claims)
                .subject(subject)                      // Standard 'sub' claim
                .header().empty().add("type","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))                           // Standard 'iat' claim
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60))                      // Standard 'exp' claim
                .signWith(getSignKey())                           // Sign with the secret key
                .compact();

        return jwtToken;
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


    public boolean validateToken(String token, String userName){
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(userName) && !isTokenExpired(token);
    }

}
