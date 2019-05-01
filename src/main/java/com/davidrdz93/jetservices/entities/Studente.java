package com.davidrdz93.jetservices.entities;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "studenti")
public class Studente
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "nome non valorizzato")
    private String nome;

    @Column(nullable = false)
    @NotEmpty(message = "cognome non valorizzato")
    private String cognome;


    @Size(min = 16, max = 16)
    private String cf = "";

    @Column
    private Long piva;

    @Column
    private String indirizzo;

    @Column
    private String citta;

    @Column
    @Size(min = 2, max = 2)
    private String prov;

    @Column
    @Digits(integer = 5, fraction = 0)
    private Integer cap;

    @Column
    private Long cell;

    @Column
    private String email;

    @OneToMany(mappedBy = "studente")
    private List<Presenza> presenze = new ArrayList<Presenza>();

    @OneToMany(mappedBy = "studente")
    private List<Iscrizione> iscrizioni = new ArrayList<Iscrizione>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public Long getPiva() {
        return piva;
    }

    public void setPiva(Long piva) {
        this.piva = piva;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Long getCell() {
        return cell;
    }

    public void setCell(Long cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Presenza> getPresenze() {
        return presenze;
    }

    public void setPresenze(List<Presenza> presenze) {
        this.presenze = presenze;
    }

    public List<Iscrizione> getIscrizioni() {
        return iscrizioni;
    }

    public void setIscrizioni(List<Iscrizione> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }
}
