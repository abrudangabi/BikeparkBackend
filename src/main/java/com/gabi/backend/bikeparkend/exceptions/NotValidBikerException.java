package com.gabi.backend.bikeparkend.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace","cause","suppressed","localizedMessage"})
public class NotValidBikerException extends Exception {
    public NotValidBikerException(String message) {
        super(message);
    }
}
