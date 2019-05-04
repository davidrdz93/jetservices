package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Presenza;
import com.davidrdz93.jetservices.entities.RegistroLezione;
import com.davidrdz93.jetservices.entities.Studente;
import com.davidrdz93.jetservices.exceptions.NotFound404Exception;
import com.davidrdz93.jetservices.repositories.PresenzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "mainPresenzaImp")
public class PresenzaServiceImp implements PresenzaService
{
    private PresenzaRepository presenzaRepository;

    @Autowired
    public PresenzaServiceImp(PresenzaRepository presenzaRepository)
    {
        this.presenzaRepository = presenzaRepository;
    }

    /*
    * Implementazione poco efficiente,
    * ma l'input non sara' mai grande
    *
    * */
    @Override
    public void updatePresenzeByLezioneId(Long lezioneId, List<Studente> studentiPresenti)
    {


        List<Presenza> presenzeAttuali = this.presenzaRepository.findByLezioneId(lezioneId)
                .orElseThrow(() -> new NotFound404Exception());

        // controllo se devo aggiungere nuove rigue

        boolean aggiungi;

        for (Studente studente : studentiPresenti)
        {
            aggiungi = true;

            for (Presenza presenza : presenzeAttuali)
                if (presenza.getStudente().getId().equals(studente.getId()))
                {
                    aggiungi = false;
                    break;
                }

            if (aggiungi)
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

            for (Studente studente : studentiPresenti)
            {
                if (studente.getId().equals(presenza.getStudente().getId()))
                {
                    cancella = false;
                    break;
                }
            }

            if (cancella)
                this.presenzaRepository.deleteById(presenza.getId());
        }


    }
}
