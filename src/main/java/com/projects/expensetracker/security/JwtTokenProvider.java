package com.projects.expensetracker.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String APP_SECRET;

    @Value("${jwt.expiration}")
    private Long EXPIRATION_TIME;

    public String generateToken(Authentication auth) {
        JwtUserDetails user = (JwtUserDetails) auth.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder().setSubject(Long.toString(user.getId())).setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();
    }

    Long getUserIdFromJWT(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getSubject());
    }

    Boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(authToken);
            return !isTokenExpired(authToken);
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            System.out.println("Invalid JWT signature.");
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }

    Boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
