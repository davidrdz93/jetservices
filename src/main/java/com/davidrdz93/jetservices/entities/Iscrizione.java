package com.davidrdz93.jetservices.entities;

import javax.persistence.*;

@Entity
@Table(name = "ISCRIZ")
public class Iscrizione
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "FK_STUD")
    private Studente studente;

    @ManyToOne
    @JoinColumn(name = "FK_COR")
    private Corso corso;


    @ManyToOne
    @JoinColumn(name = "FK_INSE")
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
