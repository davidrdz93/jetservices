package com.davidrdz93.jetservices.controllers;

import com.davidrdz93.jetservices.entities.User;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.exceptions.UsernameEsistenteException;
import com.davidrdz93.jetservices.repositories.UserRepository;
import com.davidrdz93.jetservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/users")
public class UserController
{


    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository,
                          @Qualifier("mainUsersImp") UserService userService)
    {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUtenti(@RequestParam(required = false) String nome,
                                @RequestParam(required = false) String cognome,
                                @RequestParam(required = false) String username)
    {
        return this.userService.retrieveUsers(nome, cognome, username);
    }

    @PostMapping
    public User addUtente(@RequestBody User nuovoUtente)
    {
        Optional utente = userRepository.findByUsername(nuovoUtente.getUsername());
        if (utente.isPresent())
            throw new UsernameEsistenteException();

        return userRepository.save(nuovoUtente);
    }

    @GetMapping("/{id}")
    public User getUtente(@PathVariable Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFound404Exception());
    }

    @PutMapping("/{id}")
    public User updateUtente(@RequestBody User nuovoUtente, @PathVariable Long id)
    {
        return userRepository.findById(id)
                .map(utente -> {
                    utente.setNome(nuovoUtente.getNome());
                    utente.setCognome(nuovoUtente.getCognome());
                    utente.setUsername(nuovoUtente.getUsername());
                    return userRepository.save(utente);
                })
                .orElseThrow(() -> new NotFound404Exception());
    }

    @DeleteMapping("/{id}")
    public void deleteUtente(@PathVariable Long id)
    {
        userRepository.deleteById(id);
    }
}

