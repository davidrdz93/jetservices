package com.davidrdz93.jetservices.entities;

import java.util.Date;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "REGISTRL")
public class RegistroLezione
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Date data;

    @ManyToOne
    @JoinColumn(name = "FK_COR")
    private Corso corso;

    @ManyToOne
    @JoinColumn(name = "FK_INSE")
    private Insegnante insegnante;

    @OneToMany(mappedBy = "lezione")
    private List<Presenza> presenze = new ArrayList<Presenza>();

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

    public List<Presenza> getPresenze() {
        return presenze;
    }

    public void setPresenze(List<Presenza> presenze) {
        this.presenze = presenze;
    }
}
