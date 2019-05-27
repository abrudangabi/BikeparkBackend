package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.Categorie;
import com.gabi.backend.bikeparkend.model.Concurs;
import com.gabi.backend.bikeparkend.model.Traseu;

import java.io.Serializable;

public class CreateTraseuBikepark implements Serializable {
    private Traseu traseu;
    private BikePark bikePark;

    public BikePark getBikePark() {
        return bikePark;
    }

    public Traseu getTraseu() {
        return traseu;
    }
}
