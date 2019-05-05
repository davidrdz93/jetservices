package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Presenza;
import com.davidrdz93.jetservices.entities.RegistroLezione;
import com.davidrdz93.jetservices.entities.Studente;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.PresenzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "mainPresenzaImp")
public class PresenzaServiceImp implements PresenzaService
{
    private PresenzaRepository presenzaRepository;

    @Autowired
    public PresenzaServiceImp(PresenzaRepository presenzaRepository)
    {
        this.presenzaRepository = presenzaRepository;
    }

    private class CompareStudentiById implements Comparator<Studente>
    {
        @Override
        public int compare(Studente s1, Studente s2)
        {
            return s1.getId().compareTo(s2.getId());
        }
    }

    /*
    * Utilizzo mergesort e binary search per assicurare
    * il minimo costo
    * */
    @Override
    public void updatePresenzeByLezioneId(Long lezioneId, List<Studente> studentiPresenti)
    {


        List<Presenza> presenzeAttuali = this.presenzaRepository.findByLezioneId(lezioneId)
                .orElseThrow(() -> new NotFound404Exception());

        List<Studente> studentiAttuali = presenzeAttuali
                .stream()
                .map(Presenza::getStudente)
                .collect(Collectors.toList());


        // ordino entrambe le liste per id
        // usa mergesort
        // attenzione al casting da Long a int
        studentiPresenti.sort(new CompareStudentiById());
        studentiAttuali.sort(new CompareStudentiById());

        // controllo se devo aggiungere nuove rigue

        boolean aggiungi;
        int index;

        for (Studente studente : studentiPresenti)
        {
            aggiungi = true;
            index = Collections.binarySearch(studentiAttuali, studente, new CompareStudentiById());

            if (index >= 0)
                aggiungi = false;

            if (aggiungi) // non trovato, aggiunto
            {
                Presenza presenzaNuova = new Presenza();
                RegistroLezione lezioneR = new RegistroLezione();
                lezioneR.setId(lezioneId); // mi serve una istanza con quel id

                presenzaNuova.setStudente(studente);
                presenzaNuova.setLezione(lezioneR);
                this.presenzaRepository.save(presenzaNuova);

            }

        }

        // controllo se devo togliere delle righe

        boolean cancella;

        for (Presenza presenza: presenzeAttuali)
        {
            cancella = true;
            index = Collections.binarySearch(studentiPresenti, presenza.getStudente(), new CompareStudentiById());

            if (index >= 0)
                cancella = false;

            if (cancella)
                this.presenzaRepository.deleteById(presenza.getId());
        }


    }
}
