package com.davidrdz93.jetservices.entities;

import javax.persistence.*;

@Entity
@Table(name = "ANAPRES",
       uniqueConstraints = @UniqueConstraint(columnNames = {"FK_LEZ", "FK_STUD"}))
public class Presenza
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne
    @JoinColumn(name = "FK_LEZ")
    private RegistroLezione lezione;

    @ManyToOne
    @JoinColumn(name = "FK_STUD")
    private Studente studente;

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
}
