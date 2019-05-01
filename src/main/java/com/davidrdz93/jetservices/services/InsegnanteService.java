package com.davidrdz93.jetservices.services;


import com.davidrdz93.jetservices.entities.Insegnante;

import java.util.List;

public interface InsegnanteService
{
    public List<Insegnante> getInsegnanti(String nome,
                                          String cognome,
                                          String email);
}
