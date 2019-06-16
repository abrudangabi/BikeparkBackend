package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/*@XmlRootElement
@Entity
@Table(name = "bikepark")*/
public class BikeParkB {

    /*@Id
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

    @JsonIgnore
    @OneToMany(
            mappedBy = "bikePark",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<RezervareBikePark> rezervareBikeParks = new HashSet<>();

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


    public boolean similaritateExist(Similaritati similaritati){
        boolean ok = false;
        for(Similaritati s : this.similaritati1){
            if(s.getItem_id_a().equals(similaritati.getItem_id_a()) && s.getItem_id_b().equals(similaritati.getItem_id_b())){
                ok = true;
            }
            *//*if(s.getItem_id_a().getId().equals(similaritati.getItem_id_b().getId()) && s.getItem_id_b().getId().equals(similaritati.getItem_id_a().getId())){
                ok = true;
            }*//*
        }
        return ok;
    }

    public void setSimilaritate(Similaritati similaritate){
        for(Similaritati s : this.similaritati1){
            if(s.getItem_id_a().equals(similaritate.getItem_id_a()) && s.getItem_id_b().equals(similaritate.getItem_id_b())){
                System.out.println("Chiar a gasit ceva");
                s.setSimilarity(similaritate.getSimilarity());
            }
        }
    }

    public void addSimilarite_B(Similaritati similaritati){

        if(this.similaritateExist(similaritati)){
            System.out.println("A mai existat B");
            setSimilaritate(similaritati);
        }
        else {
            System.out.println("E nou B");
            this.similaritati1.add(similaritati);
            similaritati.setItem_id_b(this);
        }
    }

    @JsonIgnore
    @OneToMany(
            mappedBy = "bikePark",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Concurs> concurs = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            //mappedBy = "item_id_a",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Similaritati> similaritati1 = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "item_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Preferinte> preferinte = new HashSet<>();

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

    public Set<Preferinte> getPreferinte() {
        return preferinte;
    }

    public void setPreferinte(Set<Preferinte> preferinte) {
        this.preferinte = preferinte;
    }

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
                '}';
    }*/
}
