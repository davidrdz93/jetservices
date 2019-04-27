package com.davidrdz93.jetservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFound404Exception extends RuntimeException
{
    public NotFound404Exception()
    {
        super("Risorsa non trovata");
    }
}
