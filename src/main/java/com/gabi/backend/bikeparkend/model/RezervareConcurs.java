package com.gabi.backend.bikeparkend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rezervareconcurs")
public class RezervareConcurs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rezervareconcurs_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concurs_id")
    @JsonIgnore
    private Concurs concurs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "biker_id")
    @JsonIgnore
    private Biker biker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Concurs getConcurs() {
        return concurs;
    }

    public void setConcurs(Concurs concurs) {
        this.concurs = concurs;
    }

    public Biker getBiker() {
        return biker;
    }

    public void setBiker(Biker biker) {
        this.biker = biker;
    }
}
