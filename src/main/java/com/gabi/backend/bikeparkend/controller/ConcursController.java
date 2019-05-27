package com.gabi.backend.bikeparkend.controller;

import com.gabi.backend.bikeparkend.controller.requests.CreateCategorieConcurs;
import com.gabi.backend.bikeparkend.controller.requests.CreateRezervareConcurs;
import com.gabi.backend.bikeparkend.model.*;
import com.gabi.backend.bikeparkend.service.GenericService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("BikeParkController")
@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/concurs")
public class ConcursController {

    @Autowired
    private GenericService userService;

    //@GetMapping("/bikepark/details/{id}")
    //    public ResponseEntity getBikeparkById(@PathVariable Long id) throws NotValidBikeparkException {
    //        BikePark bikePark = userService.getBikeparkById(id);
    //        return new ResponseEntity(bikePark, HttpStatus.OK);
    //    }
    @GetMapping("{id}/categorii")
    public @ResponseBody
    ResponseEntity getAllCategoriiByConcurs(@PathVariable Long id) {
        System.out.println("Vine in all categorii by concurs");
        List<Categorie> categories = userService.getCategoriiByConcurs(id);
        System.out.println("Mai merge ?");
        return new ResponseEntity(categories, HttpStatus.OK);
        //return new ResponseEntity(userService.getAllBikeParks(), HttpStatus.OK);
    }

    @GetMapping("details/{id}")
    public @ResponseBody
    ResponseEntity getConcursDetails(@PathVariable Long id) {
        System.out.println("Detalii concurs");
        Concurs concurs = userService.getConcursById(id);
        System.out.println(concurs.getDenumire());
        return new ResponseEntity(concurs, HttpStatus.OK);
        //return new ResponseEntity(userService.getAllBikeParks(), HttpStatus.OK);
    }

    @GetMapping("{id}/logo")
    public @ResponseBody
    ResponseEntity getConcursLogo(@PathVariable Long id) {
        Photo photo = userService.getConcursLogo(id);
        System.out.println(photo);
        return new ResponseEntity(photo, HttpStatus.OK);
        //return new ResponseEntity(userService.getAllBikeParks(), HttpStatus.OK);
    }

    @PostMapping("/rezervareconcurs/rezerva")
    public @ResponseBody ResponseEntity addRezervareConcurs(@RequestBody CreateRezervareConcurs createRezervareConcurs){
        RezervareConcurs rezervareConcurs =
                userService.createRezervareConcurs(
                        createRezervareConcurs.getConcurs(),
                        createRezervareConcurs.getRezervareConcurs()
                );
        if (rezervareConcurs==null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(rezervareConcurs,HttpStatus.CREATED);
        /*System.out.println("Se face rezervarea");
        System.out.println(rezervareConcurs.toString());
        return new ResponseEntity(userService.rezervaConcurs(rezervareConcurs), HttpStatus.OK);*/
    }

    @PostMapping("/categorie/add")
    public @ResponseBody ResponseEntity addCategorie(@RequestBody CreateCategorieConcurs createCategorieConcurs){
        Categorie categorie =
                userService.createCategorie(
                        createCategorieConcurs.getConcurs(),
                        createCategorieConcurs.getCategorie()
                );
        if (categorie==null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(categorie,HttpStatus.CREATED);
        //return new ResponseEntity(userService.addCategorie(categorie), HttpStatus.OK);
    }

    @DeleteMapping("/categorie/delete/{id}")
    ResponseEntity deleteCategorie(@PathVariable Long id) {
        return new ResponseEntity(userService.deleteCategorie(id),HttpStatus.OK);
    }

    @GetMapping("/{id}/bikepark")
    public @ResponseBody
    ResponseEntity getBikeparkByConcurs(@PathVariable Long id) {
        System.out.println("Bikepark din concurs");
        BikePark bikeParks = userService.getBikeparkByConcurs(id);
        return new ResponseEntity(bikeParks, HttpStatus.OK);
        //return new ResponseEntity(userService.getAllBikeParks(), HttpStatus.OK);
    }

    @GetMapping("/all/categorii")
    public @ResponseBody
    ResponseEntity getAllCategorii() {
        return new ResponseEntity(userService.getAllCategorii(), HttpStatus.OK);
    }

    @GetMapping("/all/concurs")
    public @ResponseBody
    ResponseEntity getAllConcurs() {
        return new ResponseEntity(userService.getAllConcurs(), HttpStatus.OK);
    }
}
