package com.davidrdz93.jetservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DESCRIZ_ANACORSI")
public class DescrizioneCorso
{
    @Column
    private String chiave;

    @Id
    @Column(name = "descriz")
    private String descrizione;

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}

