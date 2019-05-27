package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.Categorie;
import com.gabi.backend.bikeparkend.model.Concurs;
import com.gabi.backend.bikeparkend.model.RezervareBikePark;

import java.io.Serializable;

public class CreateRezervareBikepark implements Serializable {
    private RezervareBikePark rezervareBikePark;
    private BikePark bikePark;

    public RezervareBikePark getRezervareBikePark() {
        return rezervareBikePark;
    }

    public BikePark getBikePark() {
        return bikePark;
    }
}
