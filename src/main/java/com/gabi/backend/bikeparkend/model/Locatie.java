package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "locatie")
public class Locatie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locatie_id")
    private long id;

    private String tara;

    private String provincie;

    private String localitate;

    private String strada;

    private String coordonate;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biker_id")
    private Biker biker;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bikepark_id")
    private BikePark bikePark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getProvincie() {
        return provincie;
    }

    public void setProvincie(String provincie) {
        this.provincie = provincie;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getCoordonate() {
        return coordonate;
    }

    public void setCoordonate(String coordonate) {
        this.coordonate = coordonate;
    }

    public Biker getBiker() {
        return biker;
    }

    public void setBiker(Biker biker) {
        this.biker = biker;
    }

    public BikePark getBikePark() {
        return bikePark;
    }

    public void setBikePark(BikePark bikePark) {
        this.bikePark = bikePark;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "id=" + id +
                ", tara='" + tara + '\'' +
                ", provincie='" + provincie + '\'' +
                ", localitate='" + localitate + '\'' +
                ", strada='" + strada + '\'' +
                ", coordonate='" + coordonate + '\'' +
                ", biker=" + biker +
                ", bikePark=" + bikePark +
                '}';
    }

    //Dto

}
