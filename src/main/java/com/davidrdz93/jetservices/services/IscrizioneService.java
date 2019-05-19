package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Corso;
import com.davidrdz93.jetservices.entities.Insegnante;
import com.davidrdz93.jetservices.entities.Iscrizione;

import java.util.Date;
import java.util.List;

public interface IscrizioneService
{
    List<Iscrizione> getIscrizioni(Long idCorso,
                                   Long idInsegnante,
                                   Long idStudente,
                                   Date dataIscrizioneDa,
                                   Date dateIscrizioneA,
                                   Date dataIscrizioneFineDa,
                                   Date dateIscrizioneFineA);
}
