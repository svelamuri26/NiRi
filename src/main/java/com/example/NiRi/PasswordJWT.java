package com.example.NiRi;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PasswordJWT {

    private static final String SECRET_KEY = "yourSecretKey"; // Replace with a secure key

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // Token valid for 10 days
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}