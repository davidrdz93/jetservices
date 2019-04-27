package com.davidrdz93.jetservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsernameEsistenteException extends RuntimeException
{
    public UsernameEsistenteException()
    {
        super("Username esistente");
    }
}
