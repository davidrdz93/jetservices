package com.davidrdz93.jetservices.controllers;


import com.davidrdz93.jetservices.entities.RegistroLezione;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.RegistroLezioneRepository;
import com.davidrdz93.jetservices.services.RegistroLezioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("registro")
public class RegistroLezioniController
{
    private RegistroLezioneRepository registroLezioneRepository;
    private RegistroLezioniService registroLezioniService;

    @Autowired
    public RegistroLezioniController(RegistroLezioneRepository registroLezioneRepository,
                                     RegistroLezioniService registroLezioniService)
    {
        this.registroLezioneRepository = registroLezioneRepository;
        this.registroLezioniService = registroLezioniService;
    }


    @GetMapping
    public List<RegistroLezione> getRegistriLezioni(@RequestParam(required = false) Long corsoId,
                                                    @RequestParam(required = false) Long insegnanteId,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataDa,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataA)
    {
        return this.registroLezioniService.findLezioni(corsoId, insegnanteId, dataDa, dataA);
    }

    @PostMapping
    public RegistroLezione getRegistroLezioni(@RequestBody RegistroLezione registroLezione)
    {
        return this.registroLezioneRepository.save(registroLezione);
    }

    @PutMapping("/{id}")
    public RegistroLezione updateRegistroLezioni(@PathVariable Long id,
                                                 @RequestBody RegistroLezione registroLezioneUpdated)
    {
        return this.registroLezioneRepository.findById(id)
                .map(registroLezione -> {
                    registroLezione.setCorso(registroLezioneUpdated.getCorso());
                    registroLezione.setInsegnante(registroLezioneUpdated.getInsegnante());
                    registroLezione.setData(registroLezioneUpdated.getData());
                    registroLezione.setOre(registroLezioneUpdated.getOre());
                    registroLezione.setMinuti(registroLezioneUpdated.getMinuti());
                    return this.registroLezioneRepository.save(registroLezione);
                })
                .orElseThrow(()-> new NotFound404Exception());
    }

    @GetMapping("/{id}")
    public RegistroLezione getRegistroLezione(@PathVariable Long id)
    {
        return this.registroLezioneRepository.findById(id)
                .orElseThrow(() -> new NotFound404Exception());
    }

    @DeleteMapping("/{id}")
    public void deleteRegistroLezione(@PathVariable Long id)
    {
        this.registroLezioniService.deleteLezioneAndPresenze(id);
    }

}
