package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.Concurs;

public class CreateConcursBikepark {
    private Concurs concurs;
    private BikePark bikePark;

    public Concurs getConcurs() {
        return concurs;
    }

    public BikePark getBikePark() {
        return bikePark;
    }
}
