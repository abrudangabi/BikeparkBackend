package com.gabi.backend.bikeparkend.controller;


import com.gabi.backend.bikeparkend.controller.requests.RegisterBikeparkRequest;
import com.gabi.backend.bikeparkend.controller.requests.RegisterBikerRequest;
import com.gabi.backend.bikeparkend.exceptions.RegisterBikeparkException;
import com.gabi.backend.bikeparkend.exceptions.RegisterBikerException;
import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.Biker;
import com.gabi.backend.bikeparkend.model.User;
import com.gabi.backend.bikeparkend.service.GenericService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("RegisterController")
@SuppressWarnings("ALL")
@RestController
public class RegisterController {

    @Autowired
    private GenericService userService;

    @PostMapping(value = "/register/biker")
    public ResponseEntity registerApplicant(@RequestBody RegisterBikerRequest registerBikerRequest){
        Biker bikerResult =
        userService.registerBiker(
                registerBikerRequest.getUser(),
                registerBikerRequest.getBiker()
        );
        if(bikerResult==null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(bikerResult,HttpStatus.CREATED);
    }

    @PostMapping(value = "/register/bikepark")
    public ResponseEntity registerCompany(@RequestBody RegisterBikeparkRequest registerBikeparkRequest) {
        BikePark bikeParkResult =
                userService.registerBikepark(
                        registerBikeparkRequest.getUser(),
                        registerBikeparkRequest.getBikePark()
                );
        if(bikeParkResult==null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(bikeParkResult,HttpStatus.CREATED);
    }


    @PostMapping(value = "/register/checkusername")
    public ResponseEntity checkUsername(@RequestBody User user){
        boolean exists = userService.checkUsernameExists(user);
        return new ResponseEntity(exists,HttpStatus.OK);
    }
    @PostMapping(value = "/register/checkemail")
    public ResponseEntity checkEmail(@RequestBody User user){
        boolean exists = userService.checkEmailExists(user);
        return new ResponseEntity(exists,HttpStatus.OK);
    }

    @ExceptionHandler(RegisterBikerException.class)
    public @ResponseBody ResponseEntity handleRegisterApplicantExceptions(RegisterBikerException exception) {
        return new ResponseEntity(exception,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(RegisterBikeparkException.class)
    public @ResponseBody ResponseEntity handleRegisterCompanyExceptions(RegisterBikeparkException exception) {
        return new ResponseEntity(exception,HttpStatus.CONFLICT);
    }
}
