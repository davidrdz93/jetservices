package com.davidrdz93.jetservices.controllers;

import com.davidrdz93.jetservices.entities.Iscrizione;
import com.davidrdz93.jetservices.entities.RateCorsi;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.IscrizioneRepository;
import com.davidrdz93.jetservices.repositories.RateCorsiRepository;
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
    private RateCorsiRepository rateCorsiRepository;

    @Autowired
    public IscrizioneController(IscrizioneRepository iscrizioneRepository,
                                @Qualifier("mainIscrizioneImp") IscrizioneService iscrizioneService,
                                RateCorsiRepository rateCorsiRepository)
    {
        this.iscrizioneRepository = iscrizioneRepository;
        this.iscrizioneService = iscrizioneService;
        this.rateCorsiRepository = rateCorsiRepository;
    }

    @GetMapping
    public List<Iscrizione> getIscrizioni(@RequestParam(required = false) Long idCorso,
                                          @RequestParam(required = false) Long idInsegnante,
                                          @RequestParam(required = false) Long idStudente,
                                          @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataIscrizioneDa,
                                          @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataIscrizioneA,
                                          @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataFineIscrizioneDa,
                                          @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dataFineIscrizioneA)
    {
        return this.iscrizioneService.getIscrizioni(idCorso,
                                                    idInsegnante,
                                                    idStudente,
                                                    dataIscrizioneDa,
                                                    dataIscrizioneA,
                                                    dataFineIscrizioneDa,
                                                    dataFineIscrizioneA);
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
                .orElseThrow(NotFound404Exception::new);
    }

    @GetMapping("/{id}/rate")
    public List<RateCorsi> getRateByIscrizioneId(@PathVariable Long id)
    {
        return this.rateCorsiRepository.findAllByIscrizioneId(id)
                .orElseThrow(NotFound404Exception::new);
    }

    @PutMapping("/{id}")
    public Iscrizione updateIscrizione(@RequestBody Iscrizione updatedIscrizione,
                                       @PathVariable Long id)

    {
        return this.iscrizioneRepository.findById(id)
                .map((Iscrizione iscrizione) -> {
                    iscrizione.setDataIscrizione(updatedIscrizione.getDataIscrizione());
                    iscrizione.setDataFineValidita(updatedIscrizione.getDataFineValidita());
                    iscrizione.setPrezzoOra(updatedIscrizione.getPrezzoOra());
                    iscrizione.setQuotaIscrizione(updatedIscrizione.getQuotaIscrizione());
                    return this.iscrizioneRepository.save(iscrizione);
                })
                .orElseThrow(NotFound404Exception::new);
    }

    @DeleteMapping("/{id}")
    public void deleteIscrizione(@PathVariable Long id)
    {
        this.iscrizioneRepository.deleteById(id);
    }

}
