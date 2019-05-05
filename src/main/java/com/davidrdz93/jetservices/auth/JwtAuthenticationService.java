package com.davidrdz93.jetservices.auth;

import com.davidrdz93.jetservices.auth.jwt.JwtAuth;
import com.davidrdz93.jetservices.auth.jwt.TokenVerificationException;
import com.davidrdz93.jetservices.entities.User;
import com.davidrdz93.jetservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "jwt")
public class JwtAuthenticationService implements UtenteAuthenticationService
{

    private UserRepository userRepository;
    private JwtAuth jwtAuth;

    @Autowired
    public JwtAuthenticationService(UserRepository userRepository,
                                    JwtAuth jwtAuth)
    {
        this.userRepository = userRepository;
        this.jwtAuth = jwtAuth;
    }

    /**
     * @author David Rodriguez
     * @param username Username utente
     * @param password Password utente
     * @return
     */
    @Override
    public String login(String username, String password)
    {
        return userRepository.findByUsername(username)
                .filter(utente ->  utente.getPassword().equals(password))
                .map(utente -> jwtAuth.create(username))
                .orElseThrow(() -> new BadCredentialsException("utente o password errati"));
    }

    /**
     * @author David Rodriguez
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public User authenticateByToken(String token) throws AuthenticationException
    {
        try
        {
            Object username = jwtAuth.verify(token).get("username");
            return Optional.ofNullable(username)
                    .flatMap(nome -> userRepository.findByUsername(String.valueOf(nome)))
                    .orElseThrow(() -> new BadCredentialsException("Utente " + username + " no trovato"));
        }
        catch (TokenVerificationException e)
        {
            throw new BadCredentialsException("Token non valido", e);
        }
    }

    /**
     * @author David Rodriguez
     * @param username Utente da deautenticare
     * Non esiste un modo diretto per disabilitare token rilasciati ad un utente.
     * Per logout invalidare (eliminare) il token lato client
     */
    @Override
    public void logout(String username)
    { }
}
