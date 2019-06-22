package com.gabi.backend.bikeparkend.controller.requests;

import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.User;

public class RegisterBikeparkRequest {

    private User user;
    private BikePark bikePark;

    public User getUser() {
        return user;
    }

    public BikePark getBikePark() {
        return bikePark;
    }
}
