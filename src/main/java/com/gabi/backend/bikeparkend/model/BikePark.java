package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@Entity
@Table(name = "bikepark")
public class BikePark implements Serializable {

    //TODO AICI VA FI RECOMANDARE
    
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

    public void addPreferinta(Preferinte preferinte){
        this.preferinte.add(preferinte);
        preferinte.setItem_id(this);
    }

    public void addSimilarite_A(Similaritati similaritati){
        this.similaritati1.add(similaritati);
        similaritati.setItem_id_a(this);
    }

    public void addSimilarite_B(Similaritati similaritati){
        this.similaritati1.add(similaritati);
        similaritati.setItem_id_b(this);
    }

    @JsonIgnore
    @OneToMany(
            mappedBy = "bikePark",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Concurs> concurs = new HashSet<>();

    /*@JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "taste_item_similarity",
            joinColumns = { @JoinColumn(name = "item_id_a") },
            inverseJoinColumns = {@JoinColumn(name = "item_id_b") })
    private Set<Similaritati> similar = new HashSet<>();*/

    @JsonIgnore
    @OneToMany(
            //mappedBy = "item_id_a",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    /*@JoinTable(name = "taste_item_similarity",
            joinColumns = { @JoinColumn(name = "item_id_a") },
            inverseJoinColumns = {@JoinColumn(name = "item_id_b") })*/
    private Set<Similaritati> similaritati1 = new HashSet<>();

    /*@JsonIgnore
    @OneToMany(
            mappedBy = "item_id_b",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Similaritati> similaritati2 = new HashSet<>();*/

    @JsonIgnore
    @OneToMany(
            mappedBy = "item_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Preferinte> preferinte = new HashSet<>();

    /*@JsonIgnore
    @OneToMany(
            mappedBy = "item_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Preferinte> preferinte = new HashSet<>();*/

    /*@JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "taste_item_similarity", joinColumns = { @JoinColumn(name = "item_id_a") }, inverseJoinColumns = {
            @JoinColumn(name = "item_id_b") })
    private Set<BikePark> similarMovies = new HashSet<>();*/

    /*@JsonIgnore
    *//*@OneToMany(
            mappedBy = "bikePark",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )*//*
    @OneToMany
    @JoinTable(name = "taste_item_similarity", joinColumns = { @JoinColumn(name = "item_id_a") }, inverseJoinColumns = {
            @JoinColumn(name = "item_id_b") })
    private Set<Similaritati> similaritatis = new HashSet<>();*/
    //private Set<BikePark> similarBikeparks = new HashSet<>();

    /*@JsonIgnore
    @ManyToMany
    @JoinTable(name = "taste_preferences",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") })*/

    /*@JsonIgnore
    @OneToMany
    @JoinTable(name = "taste_preferences",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") })
    private Set<Preferinte> preferintes = new HashSet<>();*/
    //private final Set<BikePark> bikeParksPreferences = new HashSet<>();

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

    public void removeTraseu(Traseu traseu){
        this.trasee.remove(traseu);
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Set<Similaritati> getSimilaritati1() {
        return similaritati1;
    }

    public void setSimilaritati1(Set<Similaritati> similaritati1) {
        this.similaritati1 = similaritati1;
    }

    /*public Set<Similaritati> getSimilaritati2() {
        return similaritati2;
    }

    public void setSimilaritati2(Set<Similaritati> similaritati2) {
        this.similaritati2 = similaritati2;
    }*/


    /*public Set<Similaritati> getSimilar() {
        return similar;
    }

    public void setSimilar(Set<Similaritati> similar) {
        this.similar = similar;
    }*/

    public Set<Preferinte> getPreferinte() {
        return preferinte;
    }

    public void setPreferinte(Set<Preferinte> preferinte) {
        this.preferinte = preferinte;
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
                //", user=" + user +
                ", denumire='" + denumire + '\'' +
                ", telescaun=" + telescaun +
                ", nrLocuri=" + nrLocuri +
                ", descriere='" + descriere + '\'' +
                ", rezervareBikeParks=" + rezervareBikeParks +
                ", contact=" + contact +
                //", trasee=" + trasee +
                //", concurs=" + concurs +
                '}';
    }

    //Dto
}
