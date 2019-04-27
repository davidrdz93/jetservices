package com.davidrdz93.jetservices.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{
    @Autowired
    @Qualifier("jwt")
    private UtenteAuthenticationService utenteAuthenticationService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException
    { }

    /**
     * Andiamo ad sfruttare utenteAuthenticationService
     * per recuperare i dati relativi all'utente che effettua la richiesta
     *
     * @param username
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        Object token = authentication.getCredentials();
        return Optional.ofNullable(token)
                .flatMap(t ->
                        Optional.of(utenteAuthenticationService.authenticateByToken(String.valueOf(t)))
                                .map(user -> User.builder()
                                        .username(user.getUsername())
                                        .password(user.getPassword())
                                        .roles("user").build()))
                .orElseThrow(() -> new BadCredentialsException("Token non valido: " + token));
    }
}
