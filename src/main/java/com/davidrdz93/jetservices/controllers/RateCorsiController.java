package com.davidrdz93.jetservices.controllers;

import com.davidrdz93.jetservices.entities.RateCorsi;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.RateCorsiRepository;
import com.davidrdz93.jetservices.services.RateCorsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("rate")
public class RateCorsiController
{
    private RateCorsiRepository rateCorsiRepository;
    private RateCorsiService rateCorsiService;

    @Autowired
    public RateCorsiController(RateCorsiRepository rateCorsiRepository,
                               @Qualifier("mainRateCorsiImp") RateCorsiService rateCorsiService)
    {
        this.rateCorsiRepository = rateCorsiRepository;
        this.rateCorsiService = rateCorsiService;
    }

    @GetMapping
    public List<RateCorsi> getRateCorsi(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataScadenzaDa,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataScadenzaA)
    {
        return this.rateCorsiService.getRateCorsi(dataScadenzaDa, dataScadenzaA);
    }


    @PostMapping
    public RateCorsi addRata(@RequestBody RateCorsi newRata)
    {
        return this.rateCorsiRepository.save(newRata);
    }

    @GetMapping("/{id}")
    public RateCorsi findRata(@PathVariable Long id)
    {
        return this.rateCorsiRepository.findById(id)
                .orElseThrow(NotFound404Exception::new);
    }

    @PutMapping("/{id}")
    public RateCorsi updateRata(@RequestBody RateCorsi updatedRataCorso, @PathVariable Long id)
    {
        return this.rateCorsiRepository.findById(id)
                .map(rataCorsi -> {
                    rataCorsi.setDataPagamento(updatedRataCorso.getDataPagamento());
                    rataCorsi.setNumeroFattura(updatedRataCorso.getNumeroFattura());
                    this.rateCorsiRepository.save(rataCorsi);
                    return rataCorsi;
                })
                .orElseThrow(NotFound404Exception::new);
    }

    @DeleteMapping("/{id}")
    public void deleteRata(@PathVariable Long id)
    {
        this.rateCorsiRepository.deleteById(id);
    }
}
