package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@Entity
@Table(name = "biker")
public class Biker implements Serializable {

    //TODO AICI VA FI RECOMANDARE

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "biker_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String nume;

    private String prenume;

    private Integer aniExperienta;

    /*@OneToOne(mappedBy = "biker", cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY, optional = true)
    private Locatie locatie;*/

    @JsonIgnore
    @OneToMany(
            mappedBy = "biker",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<RezervareBikePark> rezervareBikeParks = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "biker",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<RezervareConcurs> rezervareConcurs = new HashSet<>();

    @OneToOne(mappedBy = "biker", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private Contact contact;

    private LocalDate dataNasterii;

    private LocalDate membruData;

    private String bicicleta;

    //private String disciplinaFavorita;
    @Enumerated(EnumType.STRING)
    private Disciplina disciplinaFavorita;

    @JsonIgnore
    @OneToMany(
            mappedBy = "user_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Preferinte> preferinte = new HashSet<>();

    /*@JsonIgnore
    @ManyToMany
    @JoinTable(name = "taste_preferences",
                joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") })
    private final Set<BikePark> bikeParksPreferences = new HashSet<>();*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAniExperienta() {
        return aniExperienta;
    }

    public void setAniExperienta(Integer aniExperienta) {
        this.aniExperienta = aniExperienta;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        contact.setBiker(this);
    }

    public void removeRezervareBikepark(RezervareBikePark rezervareBikePark){
        this.rezervareBikeParks.remove(rezervareBikePark);
    }

    public void removeRezervareConcurs(RezervareConcurs rezervareConcurs){
        this.rezervareConcurs.remove(rezervareConcurs);
    }

    /*public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
        locatie.setBiker(this);
    }*/

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(LocalDate dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public LocalDate getMembruData() {
        return membruData;
    }

    public void setMembruData(LocalDate membruData) {
        this.membruData = membruData;
    }

    public String getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(String bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Disciplina getDisciplinaFavorita() {
        return disciplinaFavorita;
    }

    public void setDisciplinaFavorita(Disciplina disciplinaFavorita) {
        this.disciplinaFavorita = disciplinaFavorita;
    }

    //TODO

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Set<RezervareBikePark> getRezervareBikeParks() {
        return rezervareBikeParks;
    }

    public void addRezervareBikeParks(RezervareBikePark rezervareBikeParks) {
        this.rezervareBikeParks.add(rezervareBikeParks);
        rezervareBikeParks.setBiker(this);
    }

    public Set<RezervareConcurs> getRezervareConcurs() {
        return rezervareConcurs;
    }

    public void setRezervareConcurs(RezervareConcurs rezervareConcurs) {
        this.rezervareConcurs.add(rezervareConcurs);
        rezervareConcurs.setBiker(this);
    }

    public Set<Preferinte> getPreferinte() {
        return preferinte;
    }

    public void setPreferinta(Preferinte preferinta){
        for(Preferinte p : this.preferinte){
            if(p.getItem_id().equals(preferinta.getItem_id()) && p.getUser_id().equals(preferinta.getUser_id())){
                //System.out.println("Chiar a gasit ceva");
                p.setPreference(preferinta.getPreference());
            }
        }
    }

    public boolean preferintaExist(Preferinte preferinte){
        boolean ok = false;
        for(Preferinte p : this.preferinte){
            if(p.getItem_id().equals(preferinte.getItem_id()) && p.getUser_id().equals(preferinte.getUser_id())){
                ok = true;
            }
        }
        return ok;
    }

    public void addPreferinta(Preferinte preferinte) {
        if(preferintaExist(preferinte)){
            //System.out.println("A mai existat");
            this.setPreferinta(preferinte);
        }
        else {
            //System.out.println("E nou");
            this.preferinte.add(preferinte);
            preferinte.setUser_id(this);
        }
    }

    public Integer numarRezervate(BikePark bikePark){
        Integer numar = 0;
        for (RezervareBikePark r : this.rezervareBikeParks){
            if(r.getBikePark().equals(bikePark)){
                numar++;
            }
        }
        return numar;
    }

    public void setPreferinte(Set<Preferinte> preferinte) {
        this.preferinte = preferinte;
    }

    @Override
    public String toString() {
        return "Biker{" +
                "id=" + id +
                ", user=" + user +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", aniExperienta=" + aniExperienta +
                //", locatie=" + locatie +
                ", rezervareBikeParks=" + rezervareBikeParks +
                ", rezervareConcurs=" + rezervareConcurs +
                ", dataNasterii=" + dataNasterii +
                ", membruData=" + membruData +
                ", bicicleta='" + bicicleta + '\'' +
                ", disciplinaFavorita='" + disciplinaFavorita + '\'' +
                '}';
    }
}
