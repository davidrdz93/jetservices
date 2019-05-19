package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.RateCorsi;

import java.util.Date;
import java.util.List;

public interface RateCorsiService
{
    List<RateCorsi> getRateCorsi(Date dataScadenzaDa,
                                 Date dataScadenzaA);
}
