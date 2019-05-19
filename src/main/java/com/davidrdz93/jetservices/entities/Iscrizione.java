package com.davidrdz93.jetservices.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "iscrizioni")
public class Iscrizione
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_studente", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Studente studente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_corso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Corso corso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_insegnante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Insegnante insegnante;

    @Column(nullable = false)
    private double quotaIscrizione = 0;

    @Column(nullable = false)
    private int prezzoOra = 0;

    @Column(nullable = false)
    private Date dataIscrizione;

    @Column
    private Date dataFineValidita;

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

    public double getQuotaIscrizione() {
        return quotaIscrizione;
    }

    public void setQuotaIscrizione(double quotaIscrizione) {
        this.quotaIscrizione = quotaIscrizione;
    }

    public int getPrezzoOra() {
        return prezzoOra;
    }

    public void setPrezzoOra(int prezzoOra) {
        this.prezzoOra = prezzoOra;
    }

    public Date getDataIscrizione() {
        return dataIscrizione;
    }

    public void setDataIscrizione(Date dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
    }

    public Date getDataFineValidita() {
        return dataFineValidita;
    }

    public void setDataFineValidita(Date dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }



}
