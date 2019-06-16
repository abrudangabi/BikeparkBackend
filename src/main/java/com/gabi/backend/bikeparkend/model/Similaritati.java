package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.net.ntp.TimeStamp;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taste_item_similarity")
//@IdClass(BikePark.class)
public class Similaritati implements Serializable {

    /*@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "item_id_a", nullable = false)
    private BikePark bikePark_a;*/

    /*@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "item_id_b", nullable = false)
    private BikePark bikePark_b;*/

    //@Column(name = "item_id_a")
    //@Column(name = "item_id_b")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "similaritate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id_a")
    @JsonIgnore
    private BikePark item_id_a;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id_b")
    @JsonIgnore
    private BikePark item_id_b;

    /*@Id
    //@Column(name = "item_id_a")
    private BikePark item_id_a;

    @Id
    //@Column(name = "item_id_b")
    private BikePark item_id_b;*/

    //Face similaritatea intre 2 Bikepark-uri
    private Double similarity;

    public BikePark getItem_id_a() {
        return item_id_a;
    }

    public void setItem_id_a(BikePark item_id_a) {
        this.item_id_a = item_id_a;
    }

    public BikePark getItem_id_b() {
        return item_id_b;
    }

    public void setItem_id_b(BikePark item_id_b) {
        this.item_id_b = item_id_b;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }
}
