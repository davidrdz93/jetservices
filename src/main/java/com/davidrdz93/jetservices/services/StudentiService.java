package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Studente;

import java.util.List;

public interface StudentiService
{
    List<Studente> retrieveStudenti(String nome, String cognome, String cf);
}
