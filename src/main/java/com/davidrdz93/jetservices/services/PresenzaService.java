package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Presenza;
import com.davidrdz93.jetservices.entities.Studente;

import java.util.List;

public interface PresenzaService
{
    void updatePresenzeByLezioneId(Long lezioneId, List<Studente> studentiPresenti);
}
