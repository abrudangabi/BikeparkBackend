package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.Biker;
import com.gabi.backend.bikeparkend.model.RezervareBikePark;

public class BikeparkReservationRequest {
    private Long id;

    private Biker biker;

    private RezervareBikePark rezervareBikePark;

    public BikeparkReservationRequest(Long id, Biker biker, RezervareBikePark rezervareBikePark) {
        this.id = id;
        this.biker = biker;
        this.rezervareBikePark = rezervareBikePark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Biker getBiker() {
        return biker;
    }

    public void setBiker(Biker biker) {
        this.biker = biker;
    }

    public RezervareBikePark getRezervareBikePark() {
        return rezervareBikePark;
    }

    public void setRezervareBikePark(RezervareBikePark rezervareBikePark) {
        this.rezervareBikePark = rezervareBikePark;
    }
}
