package com.example.NiRi;

import com.example.NiRi.modules.User;
import jakarta.servlet.ServletException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final PasswordJWT passwordJWT;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, PasswordJWT passwordJWT) {
        this.authenticationManager = authenticationManager;
        this.passwordJWT = passwordJWT;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {

            String username = obtainUsername(request);
            String password = obtainPassword(request);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // If authentication is successful, generate a token using PasswordJWT
            String token = passwordJWT.generateToken((User) authentication.getPrincipal());

            // Store the token in the response or send it back as needed
            // For example, you might add it to the response headers
            response.addHeader("Authorization", "Bearer " + token);

            return authentication;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
