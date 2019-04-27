package com.davidrdz93.jetservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "descrizione_corsi")
public class DescrizioneCorso
{
    @Column
    private String chiave;

    @Id
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

