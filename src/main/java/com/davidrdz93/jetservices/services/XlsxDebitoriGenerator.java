package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.RateCorsi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface XlsxDebitoriGenerator
{
    ByteArrayInputStream debitoriToExcel(List<RateCorsi> rateCorsi) throws IOException;
}
