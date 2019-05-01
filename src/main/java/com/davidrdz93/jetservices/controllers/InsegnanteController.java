package com.davidrdz93.jetservices.controllers;


import com.davidrdz93.jetservices.entities.Insegnante;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.InsegnanteRepository;
import com.davidrdz93.jetservices.services.InsegnanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("insegnanti")
public class InsegnanteController
{

    private InsegnanteRepository insegnanteRepository;
    private InsegnanteService insegnanteService;

    @Autowired
    public InsegnanteController(InsegnanteRepository insegnanteRepository,
                                @Qualifier("mainInsegnanteImp") InsegnanteService insegnanteService)
    {
        this.insegnanteRepository = insegnanteRepository;
        this.insegnanteService = insegnanteService;
    }

    @GetMapping
    public List<Insegnante> getInsegnanti(@RequestParam(required = false) String nome,
                                          @RequestParam(required = false) String cognome,
                                          @RequestParam(required = false) String email)
    {
        return this.insegnanteService.getInsegnanti(nome, cognome, email);
    }

    @PostMapping
    public Insegnante addInsegnante(@RequestBody Insegnante newInsegnante)
    {
        return this.insegnanteRepository.save(newInsegnante);
    }

    @GetMapping("/{id}")
    public Insegnante getInsegnante(@PathVariable Long id)
    {
        return this.insegnanteRepository.findById(id)
                .orElseThrow(() -> new NotFound404Exception());
    }

    @PutMapping("/{id}")
    public Insegnante updateInsegnante(@RequestBody Insegnante updatedInsegnante,
                                       @PathVariable Long id)
    {
        return this.insegnanteRepository.findById(id)
                .map(insegnante -> {
                    insegnante.setNome(updatedInsegnante.getNome());
                    insegnante.setCognome(updatedInsegnante.getCognome());
                    insegnante.setEmail(updatedInsegnante.getEmail());
                    return this.insegnanteRepository.save(insegnante);
                })
                .orElseThrow(() -> new NotFound404Exception());
    }

    @DeleteMapping("/{id}")
    public void deleteInsegnante(@PathVariable Long id)
    {
        this.insegnanteRepository.deleteById(id);
    }
}
