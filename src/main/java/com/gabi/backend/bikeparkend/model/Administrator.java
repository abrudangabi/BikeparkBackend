package com.gabi.backend.bikeparkend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "administrator")
public class Administrator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "administrator_id")
    private Long id;

    //TODO
    private String bikepark;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBikepark() {
        return bikepark;
    }

    public void setBikepark(String bikepark) {
        this.bikepark = bikepark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
