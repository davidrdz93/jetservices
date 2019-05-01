package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Corso;

import java.util.List;
import java.util.Date;

public interface CorsoService
{

    List<Corso> retrieveCorsi(String nome,
                              String lingua,
                              String livello,
                              Date dataInizioDa,
                              Date dataInizioA,
                              Date dataFineDa,
                              Date dataFineA,
                              boolean completato);
}
