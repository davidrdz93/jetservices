package com.davidrdz93.jetservices.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "registro_lezioni")
public class RegistroLezione
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date data;

    @ManyToOne
    @JoinColumn(name = "fk_corso")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Corso corso;

    @ManyToOne
    @JoinColumn(name = "fk_insegnante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Insegnante insegnante;

    @Column
    private int ore;

    @Column
    private int minuti;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public int getMinuti() {
        return minuti;
    }

    public void setMinuti(int minuti) {
        this.minuti = minuti;
    }
}
