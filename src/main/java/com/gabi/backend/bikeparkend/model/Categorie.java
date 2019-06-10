package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categorie_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concurs_id")
    @JsonIgnore
    private Concurs concurs;

    @Enumerated(EnumType.STRING)
    private Disciplina tipDisciplina;

    @Enumerated(EnumType.STRING)
    private Dificultate dificultate;

    @Enumerated(EnumType.STRING)
    private Varsta varsta;

    private Long lungime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Concurs getConcurs() {
        return concurs;
    }

    public void setConcurs(Concurs concurs) {
        this.concurs = concurs;
    }

    public Disciplina getTipDisciplina() {
        return tipDisciplina;
    }

    public void setTipDisciplina(Disciplina tipDisciplina) {
        this.tipDisciplina = tipDisciplina;
    }

    public Dificultate getDificultate() {
        return dificultate;
    }

    public void setDificultate(Dificultate dificultate) {
        this.dificultate = dificultate;
    }

    public Varsta getVarsta() {
        return varsta;
    }

    public void setVarsta(Varsta varsta) {
        this.varsta = varsta;
    }

    public Long getLungime() {
        return lungime;
    }

    public void setLungime(Long lungime) {
        this.lungime = lungime;
    }
}
