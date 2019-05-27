package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "concurs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Concurs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "concurs_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bikepark_id")
    @JsonIgnore
    private BikePark bikePark;

    private LocalDate dataStart;

    private Integer nrParticipanti;

    private Integer minimParticipanti;

    private String denumire;

    @JsonIgnore
    @OneToMany(
            mappedBy = "concurs",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Categorie> categorii = new HashSet<>();

    public void addCategorii(Categorie categorii) {
        this.categorii.add(categorii);
        categorii.setConcurs(this);
    }

    @JsonIgnore
    @OneToMany(
            mappedBy = "concurs",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<RezervareConcurs> rezervareConcurs = new HashSet<>();

    public void addRezervareConcurs(RezervareConcurs rezervareConcurs) {
        this.rezervareConcurs.add(rezervareConcurs);
        rezervareConcurs.setConcurs(this);
    }



    ///////////////////////
    //METHODS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataStart() {
        return dataStart;
    }

    public void setDataStart(LocalDate dataStart) {
        this.dataStart = dataStart;
    }

    public Set<Categorie> getCategorii() {
        return categorii;
    }



    public BikePark getBikePark() {
        return bikePark;
    }

    public void setBikePark(BikePark bikePark) {
        this.bikePark = bikePark;
    }

    public Integer getNrParticipanti() {
        return nrParticipanti;
    }

    public void setNrParticipanti(Integer nrParticipanti) {
        this.nrParticipanti = nrParticipanti;
    }

    public Integer getMinimParticipanti() {
        return minimParticipanti;
    }

    public void setMinimParticipanti(Integer minimParticipanti) {
        this.minimParticipanti = minimParticipanti;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Set<RezervareConcurs> getRezervareConcurs() {
        return rezervareConcurs;
    }

    public void removeCategorie(Categorie categorie){
        this.categorii.remove(categorie);
    }



    @Override
    public String toString() {
        return "Concurs{" +
                "id=" + id +
                ", bikePark=" + bikePark +
                ", dataStart=" + dataStart +
                ", nrParticipanti=" + nrParticipanti +
                ", minimParticipanti=" + minimParticipanti +
                ", categorii=" + categorii +
                ", rezervareConcurs=" + rezervareConcurs +
                ", denumire='" + denumire + '\'' +
                '}';
    }

    //Dto

}
