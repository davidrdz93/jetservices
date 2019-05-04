package com.davidrdz93.jetservices.controllers;

import com.davidrdz93.jetservices.entities.Iscrizione;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.IscrizioneRepository;
import com.davidrdz93.jetservices.services.IscrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("iscrizioni")
public class IscrizioneController
{

    private IscrizioneRepository iscrizioneRepository;
    private IscrizioneService iscrizioneService;

    @Autowired
    public IscrizioneController(IscrizioneRepository iscrizioneRepository,
                                @Qualifier("mainIscrizioneImp") IscrizioneService iscrizioneService)
    {
        this.iscrizioneRepository = iscrizioneRepository;
        this.iscrizioneService = iscrizioneService;
    }

    @GetMapping
    public List<Iscrizione> getIscrizioni(@RequestParam(required = false) Long idCorso,
                                          @RequestParam(required = false) Long idInsegnante,
                                          @RequestParam(required = false) Long idStudente,
                                          @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataIscrizioneDa,
                                          @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataIscrizioneA,
                                          @RequestParam(required = false) Boolean attivo)
    {
        System.out.println("Almeno sono al controller");
        return this.iscrizioneService.getIscrizioni(idCorso,
                                                    idInsegnante,
                                                    idStudente,
                                                    dataIscrizioneDa,
                                                    dataIscrizioneA,
                                                    attivo);
    }

    @PostMapping
    public Iscrizione addIscrizione(@RequestBody Iscrizione newIscrizione)
    {
        return this.iscrizioneRepository.save(newIscrizione);
    }

    @GetMapping("/{id}")
    public Iscrizione getIscrizione(@PathVariable Long id)
    {
        return this.iscrizioneRepository.findById(id)
                .orElseThrow(() -> new NotFound404Exception());
    }

    @PutMapping("/{id}")
    public Iscrizione updateIscrizione(@RequestBody Iscrizione updatedIscrizione,
                                       @PathVariable Long id)

    {
        return this.iscrizioneRepository.findById(id)
                .map((Iscrizione iscrizione) -> {
                    iscrizione.setDataIscrizione(updatedIscrizione.getDataIscrizione());
                    iscrizione.setAttivo(updatedIscrizione.getAttivo());
                    iscrizione.setDataFineValidita(updatedIscrizione.getDataFineValidita());
                    return this.iscrizioneRepository.save(iscrizione);
                })
                .orElseThrow(() -> new NotFound404Exception());
    }

    @DeleteMapping("/{id}")
    public void deleteIscrizione(@PathVariable Long id)
    {
        this.iscrizioneRepository.deleteById(id);
    }

}
