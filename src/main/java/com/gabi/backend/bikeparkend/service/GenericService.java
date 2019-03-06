package com.gabi.backend.bikeparkend.service;

import com.gabi.backend.bikeparkend.exceptions.NotValidBikeparkException;
import com.gabi.backend.bikeparkend.model.*;

import java.util.List;

public interface GenericService {

    List<BikePark> getAllBikeParks();

    List<Biker> getAllBikers();

    List<Locatie> getAllLocations();

    List<Traseu> getAllTrasee();

    List<RezervareBikePark> getAllRezervareBikePark();

    List<Categorie> getAllCategorii();

    List<Concurs> getAllConcurs();

    List<RezervareConcurs> getAllRezervareConcurs();

    List<Contact> getAllContacts();

    List<Photo> getAllPhotos();

    BikePark getBikeparkById(Long id) throws NotValidBikeparkException;

    Biker updateApplicant(Long id, Biker applicant);

    RezervareBikePark rezervaBikepark(RezervareBikePark rezervareBikePark);

    RezervareConcurs rezervaConcurs(RezervareConcurs rezervareConcurs);

    BikePark addBikepark(BikePark bikePark);

    Concurs addConcurs(Concurs concurs);

    Categorie addCategorie(Categorie categorie);

    Traseu addTraseu(Traseu traseu);

    Role addRole(Role role);
}
