package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.RegistroLezione;

import java.util.Date;
import java.util.List;

public interface RegistroLezioniService
{
    List<RegistroLezione> findLezioni(Long corsoId,
                                      Long insegnanteId,
                                      Date dataDa,
                                      Date dataA);


    void deleteLezioneAndPresenze(Long idLezione);
}
