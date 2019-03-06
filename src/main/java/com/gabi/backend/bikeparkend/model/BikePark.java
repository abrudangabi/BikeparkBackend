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

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String denumire;

    private Boolean telescaun;

    private Integer nrLocuri;

    private String descriere;

    //TODO - TREBUIE LA MAPARE bikePark LA DATABASE

    /*@OneToOne(mappedBy = "bikePark", cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY, optional = true)
    private Locatie locatie;*/

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

    @OneToOne(mappedBy = "bikePark", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = true)
    private Contact contact;

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

    /*public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
        locatie.setBikePark(this);
    }*/

    public Integer getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(Integer nrLocuriDisponibile) {
        this.nrLocuri = nrLocuriDisponibile;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        contact.setBikePark(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    /*@Override
    public String toString() {
        return "BikePark{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                //", locatie=" + locatie +
                ", nrLocuriDisponibile=" + nrLocuri +
                ", rezervareBikeParks=" + rezervareBikeParks +
                ", trasee=" + trasee +
                ", concurs=" + concurs +
                ", telescaun=" + telescaun +
                '}';
    }*/

    @Override
    public String toString() {
        return "BikePark{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", telescaun=" + telescaun +
                ", nrLocuri=" + nrLocuri +
                ", descriere='" + descriere + '\'' +
                ", rezervareBikeParks=" + rezervareBikeParks +
                ", contact=" + contact +
                ", trasee=" + trasee +
                ", concurs=" + concurs +
                '}';
    }

    //Dto
}
