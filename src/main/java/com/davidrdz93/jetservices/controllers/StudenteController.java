package com.davidrdz93.jetservices.controllers;

import com.davidrdz93.jetservices.entities.Studente;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.StudenteRepository;
import com.davidrdz93.jetservices.services.StudentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("studenti")
public class StudenteController
{
    private StudenteRepository studenteRepository;
    private StudentiService studentiService;

    @Autowired
    public StudenteController(StudenteRepository studenteRepository,
                          @Qualifier("mainStudentiImp") StudentiService studentiService)
    {
        this.studenteRepository = studenteRepository;
        this.studentiService = studentiService;
    }

    @GetMapping
    public List<Studente> getUtenti(@RequestParam(required = false) String nome,
                                    @RequestParam(required = false) String cognome,
                                    @RequestParam(required = false) String cf)
    {
        return this.studentiService.retrieveStudenti(nome, cognome, cf);
    }

    @PostMapping
    public Studente addStudente(@RequestBody Studente studente)
    {
        return studenteRepository.save(studente);
    }

    @GetMapping("/{id}")
    public Studente getStudente(@PathVariable Long id)
    {
        return studenteRepository.findById(id)
                .orElseThrow(() -> new NotFound404Exception());
    }

    @PutMapping("/{id}")
    public Studente updateStudente(@RequestBody Studente studenteUpdated, @PathVariable Long id)
    {
        return studenteRepository.findById(id)
                .map(studente -> {
                    studente.setNome(studenteUpdated.getNome());
                    studente.setCognome(studenteUpdated.getCognome());
                    studente.setCf(studenteUpdated.getCf());
                    studente.setPiva(studenteUpdated.getPiva());
                    studente.setIndirizzo(studenteUpdated.getIndirizzo());
                    studente.setCitta(studenteUpdated.getCitta());
                    studente.setProv(studenteUpdated.getProv());
                    studente.setCap(studenteUpdated.getCap());
                    studente.setEmail(studenteUpdated.getEmail());
                    studente.setCell(studenteUpdated.getCell());
                    return studenteRepository.save(studente);
                })
                .orElseThrow(() -> new NotFound404Exception());
    }

    @DeleteMapping("/{id}")
    public void deleteStudente(@PathVariable Long id)
    {
        studenteRepository.deleteById(id);
    }

}
