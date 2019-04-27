package com.davidrdz93.jetservices.entities;

import javax.persistence.*;

@Entity
@Table(name = "iscrizioni")
public class Iscrizione
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_studente")
    private Studente studente;

    @ManyToOne
    @JoinColumn(name = "fk_corso")
    private Corso corso;


    @ManyToOne
    @JoinColumn(name = "fk_insegnante")
    private Insegnante insegnante;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public Insegnante getInsegnante() {
        return insegnante;
    }

    public void setInsegnante(Insegnante insegnante) {
        this.insegnante = insegnante;
    }
}
