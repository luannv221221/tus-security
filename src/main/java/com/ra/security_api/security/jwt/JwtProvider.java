package com.ra.security_api.security.jwt;

import com.ra.security_api.security.UserPrinciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {

    @Value("${expired}")
    private long EXPIRED_TIME;
    @Value("${secret_key}")
    private String SECRET_KEY;

    private Logger log = LoggerFactory.getLogger(JwtProvider.class);
    // taoj ra token
    public String generateToken(UserPrinciple userPrinciple){
        // thoi gian song cua token
        Date dateExpiration = new Date(new Date().getTime() + EXPIRED_TIME);
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(dateExpiration).compact();

    }

    // validate token gui len
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception exception){
            log.error(exception.getMessage());
        }
        return false;
    }

    // phuong lay ve thong tin user tu token gui len tu client
    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}


