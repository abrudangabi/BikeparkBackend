package com.gabi.backend.bikeparkend.controller;

import com.gabi.backend.bikeparkend.exceptions.NotValidBikeparkException;
import com.gabi.backend.bikeparkend.exceptions.NotValidBikerException;
import com.gabi.backend.bikeparkend.model.BikePark;
import com.gabi.backend.bikeparkend.model.Biker;
import com.gabi.backend.bikeparkend.model.User;
import com.gabi.backend.bikeparkend.service.GenericService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("UserController")
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:63343")
public class UserController {

    @Autowired
    private GenericService genericService;

    @GetMapping("/user")
    public ResponseEntity getAuthenticatedUser(){
        //TODO TOKEN
        User user =genericService.getAuthenticatedUser();
        //User user = new User();
        return new ResponseEntity(user, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/user/bikepark/{id}")
    public ResponseEntity getAuthenticatedBikepark(@PathVariable Long id) throws NotValidBikeparkException {
        //TODO
        BikePark bikePark = genericService.getBikeparkById(id);
        System.out.println("Vrea Bikepark id "+id);
        //BikePark bikePark = genericService.getBikeparkById((long)1);
        return new ResponseEntity(bikePark, HttpStatus.OK);
    }
    //@PreAuthorize("hasAuthority('APPLICANT')")
    @GetMapping("/user/biker/{id}")
    public ResponseEntity getAuthenticatedBiker(@PathVariable Long id) throws NotValidBikerException/*throws NotValidApplicantException, NotAllowedApplicantException*/ {
        /*Biker applicant = genericService.getApplicantByUserId(id);
        return new ResponseEntity(applicant, HttpStatus.OK);*/
        System.out.println("Vrea Biker id "+id);
        Biker biker = genericService.getBikerById(id);
        return new ResponseEntity(biker,HttpStatus.OK);
    }

    //@ExceptionHandler(NotValidCompanyException.class)
    public @ResponseBody
    ResponseEntity handleNotValidBikeparkException(NotValidBikeparkException exception) {
        return new ResponseEntity(exception, HttpStatus.CONFLICT);
    }
    //@ExceptionHandler(NotValidApplicantException.class)
    /*public @ResponseBody
    ResponseEntity handleNotValidApplicantException(NotValidApplicantException exception) {
        return new ResponseEntity(exception,HttpStatus.CONFLICT);
    }
    //@ExceptionHandler(NotAllowedApplicantException.class)
    public @ResponseBody
    ResponseEntity handleNotAllowedApplicantException(NotValidApplicantException exception){
        return new ResponseEntity(exception, HttpStatus.CONFLICT);
    }*/


}
