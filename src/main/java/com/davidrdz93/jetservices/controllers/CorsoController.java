package com.davidrdz93.jetservices.controllers;

import com.davidrdz93.jetservices.entities.Corso;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.models.Livelli;
import com.davidrdz93.jetservices.repositories.CorsoRepository;
import com.davidrdz93.jetservices.services.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("corsi")
public class CorsoController
{

    private CorsoRepository corsoRepository;
    private CorsoService corsoService;

    @Autowired
    public CorsoController(CorsoRepository corsoRepository,
                           @Qualifier("mainCorsoImp") CorsoService corsoService)
    {
        this.corsoRepository = corsoRepository;
        this.corsoService = corsoService;
    }

    @GetMapping
    public List<Corso> getCorsi(@RequestParam(required = false) String nome,
                                @RequestParam(required = false) String lingua,
                                @RequestParam(required = false) String livello)


    {

        return this.corsoService.retrieveCorsi(nome, lingua, livello);
    }

    @PostMapping
    public Corso addCorso(@RequestBody Corso corso)
    {
        return this.corsoRepository.save(corso);
    }

    @GetMapping("/{id}")
    public Corso getCorso(@PathVariable Long id)
    {
        return this.corsoRepository.findById(id)
                .orElseThrow(() -> new NotFound404Exception());
    }

    @PutMapping("/{id}")
    public Corso updateCorso(@RequestBody Corso corsoUpdated, @PathVariable Long id)
    {
        return this.corsoRepository.findById(id)
                .map(corso -> {
                    corso.setLingua(corsoUpdated.getLingua());
                    corso.setLivello(corsoUpdated.getLivello());
                    corso.setNome(corsoUpdated.getNome());
                    corso.setNumeroOre(corsoUpdated.getNumeroOre());
                    return this.corsoRepository.save(corso);
                })
                .orElseThrow(() -> new NotFound404Exception());
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Long id)
    {
        this.corsoRepository.deleteById(id);
    }

    @GetMapping("/{id}/oreResidue")
    public double getOreResidue(@PathVariable Long id)
    {
        return this.corsoService.oreResidue(id);
    }

    @GetMapping("/livelli")
    public Livelli getLivelli()
    {
        return new Livelli();
    }
}
