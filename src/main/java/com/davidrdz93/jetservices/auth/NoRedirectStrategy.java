package com.davidrdz93.jetservices.auth;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NoRedirectStrategy implements RedirectStrategy
{
    /**
     * @author David Rodriguez
     * @param httpServletRequest
     * @param httpServletResponse
     * @param url
     * @throws IOException
     *
     * In caso di credenziali errate il server dovrà restituire un errore 401
     * di conseguenza questo metodo non dovrà fare nulla
     *
     * @todo url è "/" di default quindi c'è la redirect a questa url
     * @todo di conseguenza il controller non viene eseguito
     * @todo
     */
    @Override
    public void sendRedirect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String url) throws IOException
    { }
}
