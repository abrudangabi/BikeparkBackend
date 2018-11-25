package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "rezervarebikepark")
public class RezervareBikePark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rezervarebikepark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bikepark_id")
    @JsonIgnore
    private BikePark bikePark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "biker_id")
    @JsonIgnore
    private Biker biker;

    private LocalDate ziua;

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

    public Biker getBiker() {
        return biker;
    }

    public void setBiker(Biker biker) {
        this.biker = biker;
    }

    public LocalDate getZiua() {
        return ziua;
    }

    public void setZiua(LocalDate ziua) {
        this.ziua = ziua;
    }
}
