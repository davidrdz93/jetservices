package com.davidrdz93.jetservices.entities;

import javax.persistence.*;

@Entity
@Table(name = "presenza",
       uniqueConstraints = @UniqueConstraint(columnNames = {"fk_lezione", "fk_studente"}))
public class Presenza
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_lezione")
    private RegistroLezione lezione;

    @ManyToOne
    @JoinColumn(name = "fk_studente")
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
