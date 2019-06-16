package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.net.ntp.TimeStamp;
//import java.sql.Timestamp;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taste_preferences")
public class Preferinte implements Serializable {

    /*@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Biker biker;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private BikePark bikePark;*/

    //@Column(name = "user_id")
    //@Column(name = "item_id")

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "preferinta_id")
    private Long id;

    //@ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Biker user_id;

    //@ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private BikePark item_id;

    private Double preference;

    private TimeStamp timestamp; //= new TimeStamp(System.currentTimeMillis());
    //private TimeStamp timestamp = TimeStamp.getCurrentTime();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Biker getUser_id() {
        return user_id;
    }

    public void setUser_id(Biker user_id) {
        this.user_id = user_id;
    }

    public BikePark getItem_id() {
        return item_id;
    }

    public void setItem_id(BikePark item_id) {
        this.item_id = item_id;
    }

    public Double getPreference() {
        return preference;
    }

    public void setPreference(Double preference) {
        this.preference = preference;
    }

    public TimeStamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(TimeStamp timestamp) {
        this.timestamp = timestamp;
    }

    public Prefera toPrefera(){
        Prefera prefera = new Prefera(this.user_id.getId(),this.item_id.getId(),(double)this.preference);
        return prefera;
    }
}
