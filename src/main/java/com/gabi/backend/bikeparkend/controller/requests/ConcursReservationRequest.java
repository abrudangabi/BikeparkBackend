package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.Biker;
import com.gabi.backend.bikeparkend.model.RezervareBikePark;
import com.gabi.backend.bikeparkend.model.RezervareConcurs;

public class ConcursReservationRequest {
    private Long id;

    private Biker biker;

    private RezervareConcurs rezervareConcurs;

    public ConcursReservationRequest(Long id, Biker biker, RezervareConcurs rezervareConcurs) {
        this.id = id;
        this.biker = biker;
        this.rezervareConcurs = rezervareConcurs;
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

    public RezervareConcurs getRezervareConcurs() {
        return rezervareConcurs;
    }

    public void setRezervareConcurs(RezervareConcurs rezervareConcurs) {
        this.rezervareConcurs = rezervareConcurs;
    }
}
