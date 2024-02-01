/*package com.example.NiRi;

import com.example.NiRi.modules.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class PasswordJWT {

    private static final String SECRET_KEY = "your-secret-key";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minutes
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String encodePassword(String password) {
        return null;
    }
}*/
