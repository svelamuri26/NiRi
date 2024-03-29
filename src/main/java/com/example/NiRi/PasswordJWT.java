package com.example.NiRi;

import com.example.NiRi.modules.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class PasswordJWT {

    private static final String SECRET_KEY = "SECRET_KEY";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}