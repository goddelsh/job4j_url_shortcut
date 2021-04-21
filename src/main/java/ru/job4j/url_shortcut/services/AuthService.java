package ru.job4j.url_shortcut.services;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.config.JwtUserDetailsService;
import ru.job4j.url_shortcut.models.Account;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static ru.job4j.url_shortcut.config.JwtRequestFilter.EXPIRATION_TIME;
import static ru.job4j.url_shortcut.config.JwtRequestFilter.SECRET;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;


    public String getToken(String username, String password) throws Exception {
        this.authenticate(username, password);

        final String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        return token;

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
