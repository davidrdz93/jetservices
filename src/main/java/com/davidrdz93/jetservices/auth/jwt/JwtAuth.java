package com.davidrdz93.jetservices.auth.jwt;

import java.util.Map;

public interface JwtAuth
{

    /**
     * Crea un JWT da un username
     *
     * @author David Rodriguez
     * @param email Email dal quale creare il token
     * @return token assegnato
     */
    String create(String email);

    /**
     * Decodifica un JWT
     *
     * @author David Rodriguez
     * @param token JWT da decodificare
     * @return Mappa che devo ancora capire
     * @throws TokenVerificationException Errore nella decodificare del JWT
     */
    Map<String, Object> verify(String token) throws TokenVerificationException;
}
