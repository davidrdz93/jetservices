package com.davidrdz93.jetservices.entities;


import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "corsi")
public class Corso
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String lingua;

    @Column(nullable = false)
    private String livello = "";

    @Column(nullable = false)
    private int numeroOre;

    @Column(nullable = false)
    private int quotaIscrizione;

    @Column(nullable = false)
    private int prezzoOra;

    @Column(nullable = false)
    private Date dataInizio;

    @Column(nullable = false)
    private Date dataFine;

    @Column(nullable = false)
    private boolean complete = false;

    @Column(nullable = false)
    private boolean individuale = false;

    @OneToMany(mappedBy = "corso")
    private List<RegistroLezione> lezioni = new ArrayList<RegistroLezione>();

    @OneToMany(mappedBy = "corso")
    private List<Iscrizione> iscrizioni = new ArrayList<Iscrizione>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getLivello() {
        return livello;
    }

    public void setLivello(String livello) {
        this.livello = livello;
    }

    public int getNumeroOre() {
        return numeroOre;
    }

    public void setNumeroOre(int numeroOre) {
        this.numeroOre = numeroOre;
    }

    public int getQuotaIscrizione() {
        return quotaIscrizione;
    }

    public void setQuotaIscrizione(int quotaIscrizione) {
        this.quotaIscrizione = quotaIscrizione;
    }

    public int getPrezzoOra() {
        return prezzoOra;
    }

    public void setPrezzoOra(int prezzoOra) {
        this.prezzoOra = prezzoOra;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isIndividuale() {
        return individuale;
    }

    public void setIndividuale(boolean individuale) {
        this.individuale = individuale;
    }

    public List<RegistroLezione> getLezioni() {
        return lezioni;
    }

    public void setLezioni(List<RegistroLezione> lezioni) {
        this.lezioni = lezioni;
    }

    public List<Iscrizione> getIscrizioni() {
        return iscrizioni;
    }

    public void setIscrizioni(List<Iscrizione> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }
}
