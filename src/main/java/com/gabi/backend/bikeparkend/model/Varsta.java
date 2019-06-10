package com.gabi.backend.bikeparkend.model;

public enum Varsta {
    COPIL("14-18 ani"),
    TANAR("19-29 ani"),
    ADULT("30-39 ani"),
    BATRAN("40+ ani");

    private final String detalii;
    Varsta(String detalii) {
        this.detalii = detalii;
    }
    private String detalii() { return detalii; }
}
