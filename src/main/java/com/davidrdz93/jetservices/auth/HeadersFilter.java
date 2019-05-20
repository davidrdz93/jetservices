package com.davidrdz93.jetservices.auth;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeadersFilter extends OncePerRequestFilter
{
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)  throws ServletException, IOException
    {

        if (!response.containsHeader("Access-Control-Allow-Origin"))
            response.addHeader("Access-Control-Allow-Origin", "*");

        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");

        filterChain.doFilter(request, response);
    }
}
