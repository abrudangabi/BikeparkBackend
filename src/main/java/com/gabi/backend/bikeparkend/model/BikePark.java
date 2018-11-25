package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bikepark")
public class BikePark implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bikepark_id")
    private Long id;

    private String denumire;

    private Boolean telescaun;

    private Integer nrLocuriDisponibile;

    @OneToOne(mappedBy = "bikePark", cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY, optional = true)
    private Locatie locatie;



    @JsonIgnore
    @OneToMany(
            mappedBy = "bikePark",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<RezervareBikePark> rezervareBikeParks = new HashSet<>();

    public void addRezervareBikeParks(RezervareBikePark rezervareBikeParks) {
        this.rezervareBikeParks.add(rezervareBikeParks);
        rezervareBikeParks.setBikePark(this);
    }

    @JsonIgnore
    @OneToMany(
            mappedBy = "bikePark",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Traseu> trasee = new HashSet<>();

    public void addTraseu(Traseu traseu) {
        this.trasee.add(traseu);
        traseu.setBikePark(this);
    }

    @JsonIgnore
    @OneToMany(
            mappedBy = "bikePark",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Concurs> concurs = new HashSet<>();

    public void addConcurs(Concurs concurs) {
        this.concurs.add(concurs);
        concurs.setBikePark(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
        locatie.setBikePark(this);
    }

    public Integer getNrLocuriDisponibile() {
        return nrLocuriDisponibile;
    }

    public void setNrLocuriDisponibile(Integer nrLocuriDisponibile) {
        this.nrLocuriDisponibile = nrLocuriDisponibile;
    }

    @JsonIgnore
    public Set<Traseu> getTrasee() {
        return trasee;
    }



    public Boolean isTelescaun() {
        return telescaun;
    }

    public void setTelescaun(Boolean telescaun) {
        this.telescaun = telescaun;
    }

    public Set<RezervareBikePark> getRezervareBikeParks() {
        return rezervareBikeParks;
    }



    public Set<Concurs> getConcurs() {
        return concurs;
    }



    public Boolean getTelescaun() {
        return telescaun;
    }

    @Override
    public String toString() {
        return "BikePark{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", locatie=" + locatie +
                ", nrLocuriDisponibile=" + nrLocuriDisponibile +
                ", rezervareBikeParks=" + rezervareBikeParks +
                ", trasee=" + trasee +
                ", concurs=" + concurs +
                ", telescaun=" + telescaun +
                '}';
    }

    //Dto
}
