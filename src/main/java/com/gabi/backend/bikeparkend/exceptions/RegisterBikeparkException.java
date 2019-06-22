package com.gabi.backend.bikeparkend.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"stackTrace","cause","suppressed","localizedMessage"})
public class RegisterBikeparkException extends Exception {
    private List<String> fieldList;
    private List<String> messages;


    public RegisterBikeparkException(String message){
        super(message);
    }
    public RegisterBikeparkException(String message, List<String> fieldList, List<String> messages)
    {
        super(message);
        this.fieldList=fieldList;
        this.messages=messages;
    }
    public List<String> getFieldList() {
        return fieldList;
    }

    public List<String> getMessages() {
        return messages;
    }
}
