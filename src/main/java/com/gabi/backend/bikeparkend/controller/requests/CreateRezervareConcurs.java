package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.Concurs;
import com.gabi.backend.bikeparkend.model.RezervareConcurs;

import java.io.Serializable;

public class CreateRezervareConcurs implements Serializable {
    private Concurs concurs;
    private RezervareConcurs rezervareConcurs;

    public Concurs getConcurs() {
        return concurs;
    }

    public RezervareConcurs getRezervareConcurs() {
        return rezervareConcurs;
    }
}
