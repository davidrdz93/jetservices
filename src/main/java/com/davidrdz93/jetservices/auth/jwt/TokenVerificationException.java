package com.davidrdz93.jetservices.auth.jwt;

public class TokenVerificationException extends RuntimeException
{
    public TokenVerificationException(Throwable t)
    {
        super(t);
    }
}