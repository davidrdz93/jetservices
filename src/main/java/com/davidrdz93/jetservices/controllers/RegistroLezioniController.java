package com.davidrdz93.jetservices.controllers;


import com.davidrdz93.jetservices.entities.RegistroLezione;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.RegistroLezioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("registro")
public class RegistroLezioniController
{
    private RegistroLezioneRepository registroLezioneRepository;

    @Autowired
    public RegistroLezioniController(RegistroLezioneRepository registroLezioneRepository)
    {
        this.registroLezioneRepository = registroLezioneRepository;
    }


    @GetMapping
    public List<RegistroLezione> getRegistriLezioni()
    {
        return null;
    }

    @PostMapping
    public RegistroLezione getRegistroLezioni(@RequestBody RegistroLezione registroLezione)
    {
        return this.registroLezioneRepository.save(registroLezione);
    }

    @GetMapping("/{id}")
    public RegistroLezione getRegistroLezione(@PathVariable Long id)
    {
        return this.registroLezioneRepository.findById(id)
                .orElseThrow(() -> new NotFound404Exception());
    }

}
