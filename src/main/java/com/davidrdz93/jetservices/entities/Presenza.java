package com.davidrdz93.jetservices.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RegistroLezione lezione;

    @ManyToOne
    @JoinColumn(name = "fk_studente")
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public RegistroLezione getLezione() {
        return lezione;
    }

    public void setLezione(RegistroLezione lezione) {
        this.lezione = lezione;
    }
}
