package com.davidrdz93.jetservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "iscrizioni",
       uniqueConstraints = @UniqueConstraint(columnNames = {"fk_corso", "fk_studente"}))
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
    private Date dataIscrizione;

    @Column
    private Date dataFineValidita;

    @Column
    private Boolean attivo = true;

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

    public Date getDataIscrizione() {
        return dataIscrizione;
    }

    public Date getDataFineValidita() {
        return dataFineValidita;
    }

    public void setDataFineValidita(Date dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

    public void setDataIscrizione(Date dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }
}
