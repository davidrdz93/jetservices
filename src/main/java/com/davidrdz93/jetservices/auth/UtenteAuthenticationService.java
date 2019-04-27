package com.davidrdz93.jetservices.auth;

import com.davidrdz93.jetservices.entities.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

public interface UtenteAuthenticationService
{
    /**
     * Autentica un utente da un username e una password
     *
     * @param username Username dell'utente da autenticare
     * @param password Password utente da autenticare
     * @return
     * @throws BadCredentialsException Caso in cui le credenziali non siano valide
     */
    String login(String username, String password) throws BadCredentialsException;

    /**
     * Autentica un utente in base a un token
     *
     * @param token Token da autenticare
     * @return Utente Utente autenticato
     * @throws AuthenticationException Nel caso in cui il token non sia valido
     */
    User authenticateByToken(String token) throws AuthenticationException;

    /**
     * Deautentica un utente dal suo username
     *
     * @param username Username dell'utente da deautenticare
     */
    void logout(String username);
}
