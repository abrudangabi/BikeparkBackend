package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.Categorie;
import com.gabi.backend.bikeparkend.model.Concurs;
import com.gabi.backend.bikeparkend.model.RezervareConcurs;

import java.io.Serializable;

public class CreateCategorieConcurs implements Serializable {
    private Categorie categorie;
    private Concurs concurs;

    public Concurs getConcurs() {
        return concurs;
    }

    public Categorie getCategorie() {
        return categorie;
    }
}
