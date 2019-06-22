package com.gabi.backend.bikeparkend.service;

import com.gabi.backend.bikeparkend.controller.requests.BikeparkReservationRequest;
import com.gabi.backend.bikeparkend.controller.requests.ConcursReservationRequest;
import com.gabi.backend.bikeparkend.exceptions.NotValidBikeparkException;
import com.gabi.backend.bikeparkend.exceptions.NotValidBikerException;
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

    User getAuthenticatedUser();

    List<BikeparkReservationRequest> getAllRezervariBikeparkForBiker();

    List<RezervareBikePark> getAllRezervariBikeparkByBiker(Long id) throws NotValidBikerException;

    List<ConcursReservationRequest> getAllRezervariConcursForBiker();

    List<RezervareConcurs> getAllRezervariConcursByBiker(Long id) throws NotValidBikerException;

    BikePark getBikeparkById(Long id) throws NotValidBikeparkException;

    Biker getBikerById(Long id) throws NotValidBikerException;

    Biker updateApplicant(Long id, Biker applicant);

    Biker registerBiker(User user, Biker biker);

    boolean checkUsernameExists(User user);

    boolean checkEmailExists(User user);

    BikePark registerBikepark(User user, BikePark bikePark);

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

    Concurs deleteConcurs(Long id);

    RezervareBikePark deleteRezervareBikepark(Long id);

    RezervareConcurs deleteRezervareConcurs(Long id);

    RezervareConcurs createRezervareConcurs(Concurs concurs, RezervareConcurs rezervareConcurs);

    Categorie createCategorie(Concurs concurs, Categorie categorie);

    Concurs createConcurs(BikePark bikePark, Concurs concurs) throws NotValidBikeparkException;

    Traseu createTraseu(BikePark bikePark, Traseu traseu) throws NotValidBikeparkException;

    RezervareBikePark createRezervareBikepark(BikePark bikePark, RezervareBikePark rezervareBikePark) throws NotValidBikeparkException, NotValidBikerException;

    BikePark updateBikepark(Long id, BikePark bikePark) throws NotValidBikeparkException;

    Contact updateBikeparkContact(Long id, Contact contact);

    List<Traseu> findTraseeByBikeparkId(Long id);

    List<Concurs> findConcursuriByBikeparkId(Long id);

    Biker findBikerById(Long id) throws NotValidBikerException;

    List<BikePark> recomanda(Biker biker);

    List<BikePark> recommend(Biker user, int howMany);
}
