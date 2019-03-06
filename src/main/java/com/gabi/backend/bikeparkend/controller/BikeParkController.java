package com.gabi.backend.bikeparkend.controller;

import com.gabi.backend.bikeparkend.exceptions.NotValidBikeparkException;
import com.gabi.backend.bikeparkend.model.*;
import com.gabi.backend.bikeparkend.service.GenericService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("BikeParkController")
@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/bikepark")
public class BikeParkController {

    @Autowired
    private GenericService userService;

    //TODO

    @GetMapping("/all/bikeparks")
    public @ResponseBody
    ResponseEntity getAllBikeParks(){
        return new ResponseEntity(userService.getAllBikeParks(), HttpStatus.OK);
    }

    @GetMapping("/all/bikers")
    public @ResponseBody
    ResponseEntity getAllBikers(){
        return new ResponseEntity(userService.getAllBikers(), HttpStatus.OK);
    }

    @GetMapping("/all/locations")
    public @ResponseBody
    ResponseEntity getAllLocations(){
        return new ResponseEntity(userService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/all/trasee")
    public @ResponseBody
    ResponseEntity getAllTrasee(){
        return new ResponseEntity(userService.getAllTrasee(), HttpStatus.OK);
    }

    @GetMapping("/all/rezervarebikeparks")
    public @ResponseBody
    ResponseEntity getAllRezervareBikeparks(){
        return new ResponseEntity(userService.getAllRezervareBikePark(), HttpStatus.OK);
    }

    @GetMapping("/all/categorii")
    public @ResponseBody
    ResponseEntity getAllCategorii(){
        return new ResponseEntity(userService.getAllCategorii(), HttpStatus.OK);
    }

    @GetMapping("/all/concurs")
    public @ResponseBody
    ResponseEntity getAllConcurs(){
        return new ResponseEntity(userService.getAllConcurs(), HttpStatus.OK);
    }

    @GetMapping("/all/rezervareconcurs")
    public @ResponseBody
    ResponseEntity getAllRezervareConcurs(){
        return new ResponseEntity(userService.getAllRezervareConcurs(), HttpStatus.OK);
    }

    @GetMapping("/all/contacts")
    public @ResponseBody
    ResponseEntity getAllContacts(){
        return new ResponseEntity(userService.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/all/photos")
    ResponseEntity getAllPhotos(){
        return new ResponseEntity(userService.getAllPhotos(), HttpStatus.OK);
    }

    @GetMapping("/bikepark/details/{id}")
    public ResponseEntity getBikeparkById(@PathVariable Long id) throws NotValidBikeparkException {
        BikePark bikePark = userService.getBikeparkById(id);
        return new ResponseEntity(bikePark, HttpStatus.OK);
    }

    @PutMapping("/biker/{id}")
    public @ResponseBody
    ResponseEntity updateApplicant(@PathVariable Long id, @RequestBody Biker applicant) {
        return new ResponseEntity(userService.updateApplicant(id,applicant), HttpStatus.OK);
    }

    @PostMapping("/rezervarebikepark/rezerva")
    public @ResponseBody ResponseEntity addRezervareBikepark(@RequestBody RezervareBikePark rezervareBikePark){
        return new ResponseEntity(userService.rezervaBikepark(rezervareBikePark), HttpStatus.OK);
    }

    @PostMapping("/rezervareconcurs/rezerva")
    public @ResponseBody ResponseEntity addRezervareConcurs(@RequestBody RezervareConcurs rezervareConcurs){
        return new ResponseEntity(userService.rezervaConcurs(rezervareConcurs), HttpStatus.OK);
    }

    @PostMapping("/add/concurs")
    public @ResponseBody ResponseEntity addConcurs(@RequestBody Concurs concurs){
        return new ResponseEntity(userService.addConcurs(concurs), HttpStatus.OK);
    }

    @PostMapping("/add/bikepark")
    public @ResponseBody ResponseEntity addBikepark(@RequestBody BikePark bikePark){
        return new ResponseEntity(userService.addBikepark(bikePark), HttpStatus.OK);
    }

    @PostMapping("/add/categorie")
    public @ResponseBody ResponseEntity addCategorie(@RequestBody Categorie categorie){
        return new ResponseEntity(userService.addCategorie(categorie), HttpStatus.OK);
    }

    @PostMapping("/add/traseu")
    public @ResponseBody ResponseEntity addTraseu(@RequestBody Traseu traseu){
        return new ResponseEntity(userService.addTraseu(traseu), HttpStatus.OK);
    }

    @PostMapping("/add/role")
    public @ResponseBody ResponseEntity addRole(@RequestBody Role role){
        return new ResponseEntity(userService.addRole(role), HttpStatus.OK);
    }
}
