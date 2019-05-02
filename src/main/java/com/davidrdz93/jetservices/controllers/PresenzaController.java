package com.davidrdz93.jetservices.controllers;


import com.davidrdz93.jetservices.entities.Presenza;
import com.davidrdz93.jetservices.repositories.PresenzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("presenze")
public class PresenzaController
{

    private PresenzaRepository presenzaRepository;

    @Autowired
    public PresenzaController(PresenzaRepository presenzaRepository)
    {
        this.presenzaRepository = presenzaRepository;
    }

    @GetMapping
    public List<Presenza> getPresenze()
    {
        return null;
    }

    @PostMapping
    public Presenza addPresenza(@RequestBody Presenza presenza)
    {
        return this.presenzaRepository.save(presenza);
    }

}
