package com.davidrdz93.jetservices.entities;

import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "insegnanti")
public class Insegnante
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "insegnante")
    private List<RegistroLezione> lezioni = new ArrayList<RegistroLezione>();

    @OneToMany(mappedBy = "insegnante")
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
