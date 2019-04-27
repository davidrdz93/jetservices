package com.davidrdz93.jetservices.controllers;

import com.davidrdz93.jetservices.auth.UtenteAuthenticationService;
import com.davidrdz93.jetservices.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController
{
    private UtenteAuthenticationService utenteAuthenticationService;

    public AuthController(@Qualifier("jwt") UtenteAuthenticationService utenteAuthenticationService)
    {
        this.utenteAuthenticationService = utenteAuthenticationService;
    }

    @PostMapping
    @RequestMapping("login")
    public Object login(@RequestBody User utente)
    {
        try
        {
            return utenteAuthenticationService.login(utente.getUsername(), utente.getPassword());
        }
        catch (BadCredentialsException e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("test")
    public String test()
    {
        return "";
    }
}
