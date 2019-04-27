package com.davidrdz93.jetservices.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Tutte le richiesta da autorizzare dovranno essere processate da un apposito authentication filter
 */

public class TokenAuthenticatorFilter extends AbstractAuthenticationProcessingFilter
{

    public TokenAuthenticatorFilter(RequestMatcher matcher)
    {
        super(matcher);
    }


    /**
     * Estrai dall'header della richiesta la voe Authorization
     * valorizza l'oggetto UsernamePasswordAuthenticationToken con il token ricevuto
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    {
        String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .orElseThrow(() -> new BadCredentialsException("Manca token per autenticazione"));

        Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
        return getAuthenticationManager().authenticate(auth);
    }

    /**
     * Questo metodo è necessario per non avere una redirect a root '/'
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult) throws IOException, ServletException
    {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
