package com.gabi.backend.bikeparkend.controller;

import com.gabi.backend.bikeparkend.exceptions.NotValidBikerException;
import com.gabi.backend.bikeparkend.service.GenericService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unchecked")
@Api("ApplicantController")
@RestController
@RequestMapping("/api/biker")
@CrossOrigin(origins = "*")
public class BikerController {
    @Autowired
    private GenericService userService;

    //TODO SCOT ASTA
    //@PreAuthorize("hasAuthority('APPLICANT')")
    @GetMapping("/rezervariRequests")
    public @ResponseBody
    ResponseEntity getAllBikeparkReservationRequest(){
        return new ResponseEntity(userService.getAllRezervariBikeparkForBiker(), HttpStatus.OK);
    }

    //TODO SCOT ASTA
    //@PreAuthorize("hasAuthority('APPLICANT')")
    @GetMapping("/internshipRequests")
    public @ResponseBody
    ResponseEntity getAllConcursReservationRequests(){
        return new ResponseEntity(userService.getAllRezervariConcursForBiker(),HttpStatus.OK);
    }

    //TODO BY BIKEPARK
    //@PreAuthorize("hasAuthority('APPLICANT')")
    @GetMapping("/rezervariByBiker/{id}")
    public @ResponseBody
    ResponseEntity getRezervariBikeparkByBiker(@PathVariable Long id) throws NotValidBikerException {
        System.out.println("getRezervariBikeparkByBiker : " + id);
        return new ResponseEntity(userService.getAllRezervariBikeparkByBiker(id), HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('APPLICANT')")
    @GetMapping("/inscrieriByBiker/{id}")
    public @ResponseBody
    ResponseEntity getRezervariConcursByBiker(@PathVariable Long id) throws NotValidBikerException {
        System.out.println("getRezervariConcursByBiker : " + id);
        return new ResponseEntity(userService.getAllRezervariConcursByBiker(id),HttpStatus.OK);
    }

    //TODO DELETE REZERVARE BIKEPARK

    @DeleteMapping("/rezervarebikepark/delete/{id}")
    public ResponseEntity deleteRezervareBikepark(@PathVariable Long id) {
        System.out.println("Sterge RezervareBikepark : " + id);
        return new ResponseEntity(userService.deleteRezervareBikepark(id),HttpStatus.OK);
    }

    @DeleteMapping("/rezervareconcurs/delete/{id}")
    public ResponseEntity deleteRezervareConcurs(@PathVariable Long id) {
        System.out.println("Sterge RezervareConcurs : " + id);
        return new ResponseEntity(userService.deleteRezervareConcurs(id),HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('APPLICANT')")
    /*@GetMapping("/rezervariRequests")
    public @ResponseBody
    ResponseEntity getAllInternshipRequests(){
        return new ResponseEntity(userService.getAllRezervariBikeparkForBiker(), HttpStatus.OK);
    }*/
}
