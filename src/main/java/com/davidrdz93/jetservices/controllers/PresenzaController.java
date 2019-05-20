package com.davidrdz93.jetservices.controllers;


import com.davidrdz93.jetservices.entities.Presenza;
import com.davidrdz93.jetservices.entities.Studente;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.PresenzaRepository;
import com.davidrdz93.jetservices.services.PresenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("presenze")
public class PresenzaController
{

    private PresenzaRepository presenzaRepository;
    private PresenzaService presenzaService;


    @Autowired
    public PresenzaController(PresenzaRepository presenzaRepository,
                              @Qualifier("mainPresenzaImp") PresenzaService presenzaService)
    {
        this.presenzaRepository = presenzaRepository;
        this.presenzaService = presenzaService;
    }

    @GetMapping("/registro/{id}")
    public List<Presenza> getPresenzaByRegistroId(@PathVariable Long id)
    {
        return this.presenzaRepository.findByLezioneId(id)
                .orElseThrow(NotFound404Exception::new);
    }

    @GetMapping("/studente/{id}")
    public List<Presenza> getPresenzeByStudenteId(@PathVariable Long id)
    {
        return this.presenzaRepository.findByStudenteId(id)
                .orElseThrow(NotFound404Exception::new);
    }

    @PatchMapping("/registro/{id}")
    public void updatePresenzeByRegistroId(@RequestBody List<Studente> studentiPresenti,
                                           @PathVariable Long id)
    {
        this.presenzaService.updatePresenzeByLezioneId(id, studentiPresenti);
    }

    @PostMapping
    public Presenza addPresenza(@RequestBody Presenza presenza)
    {
        return this.presenzaRepository.save(presenza);
    }

    @DeleteMapping("/{id}")
    public void deletePresenza(@PathVariable Long id)
    {
        this.presenzaRepository.deleteById(id);
    }

}
