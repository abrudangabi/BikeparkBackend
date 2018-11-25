package com.gabi.backend.bikeparkend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "traseu")
public class Traseu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "traseu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bikepark_id")
    @JsonIgnore
    private BikePark bikePark;

    private String denumire;

    private Long lungime;

    private String tipTraseu;

    private String dificultate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BikePark getBikePark() {
        return bikePark;
    }

    public void setBikePark(BikePark bikePark) {
        this.bikePark = bikePark;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Long getLungime() {
        return lungime;
    }

    public void setLungime(Long lungime) {
        this.lungime = lungime;
    }

    public String getTipTraseu() {
        return tipTraseu;
    }

    public void setTipTraseu(String tipTraseu) {
        this.tipTraseu = tipTraseu;
    }

    public String getDificultate() {
        return dificultate;
    }

    public void setDificultate(String dificultate) {
        this.dificultate = dificultate;
    }

    @Override
    public String toString() {
        return "Traseu{" +
                "id=" + id +
                ", bikePark=" + bikePark +
                ", denumire='" + denumire + '\'' +
                ", lungime=" + lungime +
                ", tipTraseu='" + tipTraseu + '\'' +
                ", dificultate='" + dificultate + '\'' +
                '}';
    }
}
