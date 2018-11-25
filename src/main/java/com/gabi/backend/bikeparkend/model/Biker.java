package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "biker")
public class Biker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "biker_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String nume;

    private String prenume;

    private Integer aniExperienta;

    @OneToOne(mappedBy = "biker", cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY, optional = true)
    private Locatie locatie;

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

    private LocalDate dataNasterii;

    private LocalDate membruData;

    private String bicicleta;

    private String disciplinaFavorita;

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

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
        locatie.setBiker(this);
    }

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

    public String getDisciplinaFavorita() {
        return disciplinaFavorita;
    }

    public void setDisciplinaFavorita(String disciplinaFavorita) {
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

    @Override
    public String toString() {
        return "Biker{" +
                "id=" + id +
                ", user=" + user +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", aniExperienta=" + aniExperienta +
                ", locatie=" + locatie +
                ", rezervareBikeParks=" + rezervareBikeParks +
                ", rezervareConcurs=" + rezervareConcurs +
                ", dataNasterii=" + dataNasterii +
                ", membruData=" + membruData +
                ", bicicleta='" + bicicleta + '\'' +
                ", disciplinaFavorita='" + disciplinaFavorita + '\'' +
                '}';
    }
}
