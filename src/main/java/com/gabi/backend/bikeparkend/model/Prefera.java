package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.net.ntp.TimeStamp;

import javax.persistence.*;

public class Prefera {

    private Long user_id;

    private Long item_id;

    private Double preference;

    public Prefera(Long user_id, Long item_id, Double preference) {
        this.user_id = user_id;
        this.item_id = item_id;
        this.preference = preference;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Double getPreference() {
        return preference;
    }

    public void setPreference(Double preference) {
        this.preference = preference;
    }

    public Preferinte toPreferinta(){
        Preferinte preferinte = new Preferinte();
        return preferinte;
    }
}
