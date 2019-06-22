package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.Biker;
import com.gabi.backend.bikeparkend.model.User;

public class RegisterBikerRequest {

    private User user;
    private Biker biker;

    public User getUser() {
        return user;
    }

    public Biker getBiker() {
        return biker;
    }
}
