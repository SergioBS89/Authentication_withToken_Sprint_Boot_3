package com.spring.login.registration.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "bde6a99c011265fb10894ec36d5c55b4970c5ebfdcdcfdc89ef48f583b952d88";

    /**
     * Function to extract the username from the token
     */
    public String extractUsername(final String jwt) {
        return this.extractClaim(jwt, Claims::getSubject);
    }

    /**
     * This method is used to extract a specific claim from a JWT token. It takes a token and a function (claimsResolver) that is used to obtain the desired claim.
     * The function is applied to the claims contained in the token, and the result is returned in the specified type <T>.
     * @param token
     * @param claimsResolver
     * @param <T> Generic value
     * @return
     */
    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generate a JWT token with a custom set of claims provided in listOfClaims and with user information provided in userDetails.
     */
    public String generateToken(final Map<String, Object> listOfClaims, final UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(listOfClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(this.getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generate a JWT token without custom claims, using only the user details provided in userDetails.
     */
    public String generateToken(final UserDetails userDetails) {
        return this.generateToken(new HashMap<>(), userDetails);
    }

    /**
     * This method is used to validate if a JWT token is valid. It checks if the user on the token matches the user details provided and if the token has expired.
     */
    public boolean isTokenValid(final String token, final UserDetails userDetails) {
        String username = this.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    }

    private boolean isTokenExpired(final String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(final String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    /**
     * This function is used to extract all the claims from JWTS Token
     */
    private Claims extractAllClaims(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * This method is used to obtain a signing key from a SECRET_KEY in Base64 format.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtService.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
