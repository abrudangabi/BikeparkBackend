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

    List<Categorie> getCategoriiByConcurs(Long idConcurs);

    BikePark getBikeparkByConcurs(Long idConcurs);

    Concurs getConcursById(Long idConcurs);

    Photo getConcursLogo(Long idConcurs);

    Categorie deleteCategorie(Long id);

    Traseu deleteTraseu(Long id);

    RezervareConcurs createRezervareConcurs(Concurs concurs, RezervareConcurs rezervareConcurs);

    Categorie createCategorie(Concurs concurs, Categorie categorie);

    Traseu createTraseu(BikePark bikePark, Traseu traseu) throws NotValidBikeparkException;

    RezervareBikePark createRezervareBikepark(BikePark bikePark, RezervareBikePark rezervareBikePark) throws NotValidBikeparkException;

    BikePark updateBikepark(Long id, BikePark bikePark) throws NotValidBikeparkException;

    Contact updateBikeparkContact(Long id, Contact contact);

    List<Traseu> findTraseeByBikeparkId(Long id);
}
