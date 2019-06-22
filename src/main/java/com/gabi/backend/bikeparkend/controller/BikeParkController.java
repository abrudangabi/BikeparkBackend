package com.gabi.backend.bikeparkend.controller;

import com.gabi.backend.bikeparkend.controller.requests.CreateCategorieConcurs;
import com.gabi.backend.bikeparkend.controller.requests.CreateConcursBikepark;
import com.gabi.backend.bikeparkend.controller.requests.CreateRezervareBikepark;
import com.gabi.backend.bikeparkend.controller.requests.CreateTraseuBikepark;
import com.gabi.backend.bikeparkend.exceptions.NotValidBikeparkException;
import com.gabi.backend.bikeparkend.exceptions.NotValidBikerException;
import com.gabi.backend.bikeparkend.model.*;
import com.gabi.backend.bikeparkend.service.GenericService;
import io.swagger.annotations.Api;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.GenericEntity;
import java.util.ArrayList;
import java.util.List;

@Api("BikeParkController")
@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/bikepark")
public class BikeParkController {

    /*@Autowired
    private ItemBasedRecommender recommender;*/

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

    /*@GetMapping("/all/categorii")
    public @ResponseBody
    ResponseEntity getAllCategorii(){
        return new ResponseEntity(userService.getAllCategorii(), HttpStatus.OK);
    }*/

    /*@GetMapping("/all/concurs")
    public @ResponseBody
    ResponseEntity getAllConcurs(){
        return new ResponseEntity(userService.getAllConcurs(), HttpStatus.OK);
    }*/

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
    public ResponseEntity getAllPhotos(){
        return new ResponseEntity(userService.getAllPhotos(), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
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
    public @ResponseBody ResponseEntity addRezervareBikepark(@RequestBody CreateRezervareBikepark createRezervareBikepark) throws NotValidBikeparkException, NotValidBikerException{
        System.out.println(createRezervareBikepark.getRezervareBikePark().getZiua());
        RezervareBikePark rezervareBikePark =
                userService.createRezervareBikepark(
                        createRezervareBikepark.getBikePark(),
                        createRezervareBikepark.getRezervareBikePark()
                );
        if (rezervareBikePark==null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        //userService.resetarePreferinte();
        BikePark bikePark = userService.getBikeparkById(rezervareBikePark.getBikePark().getId());
        return new ResponseEntity(bikePark,HttpStatus.CREATED);
        //return new ResponseEntity(userService.rezervaBikepark(rezervareBikePark), HttpStatus.OK);
    }

    /*@PostMapping("/rezervareconcurs/rezerva")
    public @ResponseBody ResponseEntity addRezervareConcurs(@RequestBody RezervareConcurs rezervareConcurs){
        return new ResponseEntity(userService.rezervaConcurs(rezervareConcurs), HttpStatus.OK);
    }*/

    @PostMapping("/add/concurs")
    public @ResponseBody ResponseEntity addConcurs(@RequestBody CreateConcursBikepark createConcursBikepark) throws NotValidBikeparkException{
        Concurs concurs =
                userService.createConcurs(
                        createConcursBikepark.getBikePark(),
                        createConcursBikepark.getConcurs()
                );
        if (concurs==null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(concurs,HttpStatus.CREATED);
        //return new ResponseEntity(userService.addConcurs(concurs), HttpStatus.OK);
    }

    @PostMapping("/add/bikepark")
    public @ResponseBody ResponseEntity addBikepark(@RequestBody BikePark bikePark){
        return new ResponseEntity(userService.addBikepark(bikePark), HttpStatus.OK);
    }

    @PostMapping("/add/categorie")
    public @ResponseBody ResponseEntity addCategorie(@RequestBody Categorie categorie){
        return new ResponseEntity(userService.addCategorie(categorie), HttpStatus.OK);
    }

    /*@PostMapping("/add/traseu")
    public @ResponseBody ResponseEntity addTraseu(@RequestBody Traseu traseu){
        return new ResponseEntity(userService.addTraseu(traseu), HttpStatus.OK);
    }*/

    @PostMapping("/add/role")
    public @ResponseBody ResponseEntity addRole(@RequestBody Role role){
        return new ResponseEntity(userService.addRole(role), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public @ResponseBody
    ResponseEntity updateBikepark(@PathVariable Long id, @RequestBody BikePark bikePark) throws NotValidBikeparkException {
        System.out.println("Face edit in Bikepark");
        return new ResponseEntity(userService.updateBikepark(id,bikePark), HttpStatus.OK);
    }

    @PutMapping("/edit/contact/{id}")
    public @ResponseBody
    ResponseEntity updateBikeparkContact(@PathVariable Long id, @RequestBody Contact contact) {
        System.out.println("Face edit in contacte la Bikepark");
        return new ResponseEntity(userService.updateBikeparkContact(id,contact), HttpStatus.OK);
    }

    @GetMapping("/traseu/{id}")
    public ResponseEntity getTraseeByBikepark(@PathVariable Long id) {
        System.out.println("Cheama traseele");
        List<Traseu> traseuList = userService.findTraseeByBikeparkId(id);
                // userService.getAllTrasee();//userService.findTraseeByBikeparkId(id);
        System.out.println("Cate sunt : " + traseuList.size());
        return new ResponseEntity(traseuList, HttpStatus.OK);
    }

    @GetMapping("/concursByBikepark/{id}")
    public ResponseEntity getConcursuriByBikepark(@PathVariable Long id) {
        System.out.println("Vrea getConcursuriByBikepark id "+id);
        List<Concurs> concursList = userService.findConcursuriByBikeparkId(id);
        System.out.println("Cate sunt : " + concursList.size());
        return new ResponseEntity(concursList, HttpStatus.OK);
    }

    @DeleteMapping("/traseu/delete/{id}")
    public ResponseEntity deleteTraseu(@PathVariable Long id) {
        System.out.println("Sterge traseul : " + id);
        return new ResponseEntity(userService.deleteTraseu(id),HttpStatus.OK);
    }

    @DeleteMapping("/concurs/delete/{id}")
    public ResponseEntity deleteConcurs(@PathVariable Long id) {
        System.out.println("Vrea deleteConcurs id "+id);
        System.out.println("Sterge concursul : " + id);
        return new ResponseEntity(userService.deleteConcurs(id),HttpStatus.OK);
    }

    @PostMapping("/add/traseu")
    public @ResponseBody ResponseEntity addTraseu(@RequestBody CreateTraseuBikepark createTraseuBikepark) throws NotValidBikeparkException{
        Traseu traseu =
                userService.createTraseu(
                        createTraseuBikepark.getBikePark(),
                        createTraseuBikepark.getTraseu()
                );
        if (traseu==null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(traseu,HttpStatus.CREATED);
        //return new ResponseEntity(userService.addCategorie(categorie), HttpStatus.OK);
    }

    /*@GetMapping("/{userId}/recommendations")
    public ResponseEntity recommend(@PathVariable Long userId*//*, @RequestBody Integer limit*//*) {
        Integer limit = 2;
        System.out.println("Intra aici");
        System.out.println("User id " + userId);
        //userService.curataSimilaritati();
        List<BikePark> vals = new ArrayList<>();
        GenericEntity<List<BikePark>> res = new GenericEntity<List<BikePark>>(vals) {};
        *//*try {
            userService.verifBD(userService.findBikerById(userId));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }*//*
        //userService.calculateSimilarities();
        try {
            System.out.println("Ce naiba ii ia atata");
            userService.recomanda(userService.findBikerById(userId));
        }catch (NotValidBikerException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(res,HttpStatus.OK);
        }

        //try{
            System.out.println("Acum e gata ala");
            //vals = userService.recommend(userService.findBikerById(userId), limit);
            for(BikePark m : vals){
                System.out.println(m.toString());
            }
            res = new GenericEntity<List<BikePark>>(vals) {};
            return new ResponseEntity(res, HttpStatus.OK);
        *//*} catch (NotValidBikerException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(res,HttpStatus.OK);
        }*//*

        *//*List<BikePark> vals = userService.recommend(userService.findBikerById(userId), limit);
        for(BikePark m : vals){
            System.out.println(m.toString());
        }
        GenericEntity<List<BikePark>> res = new GenericEntity<List<BikePark>>(vals) {};*//*
        //GenericEntity<List<BikePark>> res = new GenericEntity<List<BikePark>>(vals) {};

        //return new ResponseEntity(res, HttpStatus.OK);
    }*/

    @GetMapping("/{userId}/recommendations")
    public ResponseEntity recommend(@PathVariable Long userId/*, @RequestBody Integer limit*/) {
        System.out.println("Intra aici");
        System.out.println("User id " + userId);
        List<BikePark> vals = new ArrayList<>();
        //GenericEntity<List<BikePark>> res = new GenericEntity<List<BikePark>>(vals) {};
        try {
            System.out.println("Ce naiba ii ia atata");
            vals = userService.recomanda(userService.findBikerById(userId));
        }catch (NotValidBikerException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(vals,HttpStatus.OK);
        }

        System.out.println("Acum e gata ala");
        /*for(BikePark m : vals){
            System.out.println(m.toString());
        }*/
        //res = new GenericEntity<List<BikePark>>(vals) {};
        return new ResponseEntity(vals, HttpStatus.OK);
    }
}
