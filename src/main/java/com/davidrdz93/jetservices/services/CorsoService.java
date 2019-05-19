package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.Corso;

import java.util.List;

public interface CorsoService
{

    List<Corso> retrieveCorsi(String nome,
                              String lingua,
                              String livello);

    double oreResidue(Long corsoId);
}
