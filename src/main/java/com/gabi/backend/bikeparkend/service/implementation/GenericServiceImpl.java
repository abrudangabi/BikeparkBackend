package com.gabi.backend.bikeparkend.service.implementation;

import com.gabi.backend.bikeparkend.exceptions.NotValidBikeparkException;
import com.gabi.backend.bikeparkend.model.*;
import com.gabi.backend.bikeparkend.repository.*;
import com.gabi.backend.bikeparkend.repository.BikerRepository;
import com.gabi.backend.bikeparkend.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class GenericServiceImpl implements GenericService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private BikeParkRepository bikeParkRepository;

    @Autowired
    private BikerRepository bikerRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private ConcursRepository concursRepository;

    @Autowired
    private LocatieRepository locatieRepository;

    @Autowired
    private RezervareBikeParkRepository rezervareBikeParkRepository;

    @Autowired
    private RezervareConcursRepository rezervareConcursRepository;

    @Autowired
    private TraseuRepository traseuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    private void loadData() {
        initRoles();
        initDatabase();
    }

    private void initRoles(){

    }

    private void initDatabase(){

    }

    @Override
    public List<BikePark> getAllBikeParks(){
        return bikeParkRepository.findAll();
    }

    @Override
    public List<Biker> getAllBikers(){
        return bikerRepository.findAll();
    }

    @Override
    public List<Locatie> getAllLocations(){
        return locatieRepository.findAll();
    }

    @Override
    public List<Traseu> getAllTrasee(){
        return traseuRepository.findAll();
    }

    @Override
    public List<RezervareBikePark> getAllRezervareBikePark(){
        return rezervareBikeParkRepository.findAll();
    }

    @Override
    public List<Categorie> getAllCategorii(){
        return categorieRepository.findAll();
    }

    @Override
    public List<Concurs> getAllConcurs(){
        return concursRepository.findAll();
    }

    @Override
    public List<RezervareConcurs> getAllRezervareConcurs(){
        return rezervareConcursRepository.findAll();
    }

    @Override
    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    @Override
    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }

    @Override
    public BikePark getBikeparkById(Long id) throws NotValidBikeparkException {
        Optional<BikePark> bikePark = bikeParkRepository.findById(id);
        if (bikePark.isPresent()) {
            System.out.println(bikePark.get().toString());
            return bikePark.get();
        }
        throw new NotValidBikeparkException("No company with this user ID!");
    }

    private void updateFirstNameBiker(Biker currentApplicant, Biker applicant) {
        if (applicant.getNume() != null && !currentApplicant.getNume().equals(applicant.getNume()))
            currentApplicant.setNume(applicant.getNume());
    }

    private void updateBikeparkFields(BikePark currentBikepark, BikePark bikePark){
        if (bikePark.getDenumire() != null && !currentBikepark.getDenumire().equals(bikePark.getDenumire()))
            currentBikepark.setDenumire(bikePark.getDenumire());
        if (bikePark.getDescriere() != null && !currentBikepark.getDescriere().equals(bikePark.getDescriere()))
            currentBikepark.setDescriere(bikePark.getDescriere());
        if (bikePark.getNrLocuri() != null && !currentBikepark.getNrLocuri().equals(bikePark.getNrLocuri()))
            currentBikepark.setNrLocuri(bikePark.getNrLocuri());
    }

    private void updateBikeparkContactFields(Contact currentContact, Contact contact){
        if (contact.getPhoneNumber() != null && !currentContact.getPhoneNumber().equals(contact.getPhoneNumber()))
            currentContact.setPhoneNumber(contact.getPhoneNumber());
        if (contact.getFacebookLink() != null && !currentContact.getFacebookLink().equals(contact.getFacebookLink()))
            currentContact.setFacebookLink(contact.getFacebookLink());
        if (contact.getWebsite() != null && !currentContact.getWebsite().equals(contact.getWebsite()))
            currentContact.setWebsite(contact.getWebsite());

        if (contact.getLocatie().getTara() != null && !currentContact.getLocatie().getTara().equals(contact.getLocatie().getTara()))
            currentContact.getLocatie().setTara(contact.getLocatie().getTara());
        if (contact.getLocatie().getLocalitate() != null && !currentContact.getLocatie().getLocalitate().equals(contact.getLocatie().getLocalitate()))
            currentContact.getLocatie().setLocalitate(contact.getLocatie().getLocalitate());
        if (contact.getLocatie().getProvincie() != null && !currentContact.getLocatie().getProvincie().equals(contact.getLocatie().getProvincie()))
            currentContact.getLocatie().setProvincie(contact.getLocatie().getLocalitate());
        if (contact.getLocatie().getStrada() != null && !currentContact.getLocatie().getStrada().equals(contact.getLocatie().getStrada()))
            currentContact.getLocatie().setStrada(contact.getLocatie().getStrada());
        if (contact.getLocatie().getNumber() != null && !currentContact.getLocatie().getNumber().equals(contact.getLocatie().getNumber()))
            currentContact.getLocatie().setNumber(contact.getLocatie().getNumber());
    }

    private Biker findApplicantById(Long id) {

        Optional<Biker> applicantOptional = bikerRepository.findById(id);
        /*if (!applicantOptional.isPresent()) {
            throw new NotValidApplicantException("Applicant with ID:" + id + " doesn't exist!");
        }*/
        Biker applicant = applicantOptional.get();

        return applicant;

    }

    @Override
    public Biker updateApplicant(Long id, Biker applicant) {

        Biker currentApplicant = findApplicantById(id);

        /*if (!checkTheUser(currentApplicant.getUser()))
            throw new NotAllowedApplicantException("You don't have permissions to update!");

        if (currentApplicant.getDescription() == null) {
            currentApplicant.setDescription("");
        }*/

        updateFirstNameBiker(currentApplicant, applicant);

        return bikerRepository.save(currentApplicant);
    }

    @Override
    public RezervareBikePark rezervaBikepark(RezervareBikePark rezervareBikePark){
        if (rezervareBikeParkRepository.existsById(rezervareBikePark.getId())){
            rezervareBikePark.setId(rezervareBikePark.getId()+1);
        }
        return rezervareBikeParkRepository.save(rezervareBikePark);
    }

    @Override
    public RezervareConcurs rezervaConcurs(RezervareConcurs rezervareConcurs){
        return  rezervareConcursRepository.save(rezervareConcurs);
    }

    @Override
    public BikePark addBikepark(BikePark bikePark){
        return bikeParkRepository.save(bikePark);
    }

    @Override
    public Concurs addConcurs(Concurs concurs){
        return concursRepository.save(concurs);
    }

    @Override
    public Categorie addCategorie(Categorie categorie){
        return categorieRepository.save(categorie);
    }

    @Override
    public Traseu addTraseu(Traseu traseu){
        return traseuRepository.save(traseu);
    }

    @Override
    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    @Override
    public List<Categorie> getCategoriiByConcurs(Long idConcurs){
        Optional<List<Categorie>> categoriesOptional = categorieRepository.findAllByConcurs_Id(idConcurs);
        if (!categoriesOptional.isPresent()) {
            //throw new NotValidApplicantException("Applicant with ID:" + id + " doesn't exist!");
        }
        List<Categorie> categories = categoriesOptional.get();
        return categories;
        //return categorieRepository.findCategorieByConcurs(idConcurs);
    }

    @Override
    public BikePark getBikeparkByConcurs(Long idConcurs){
        System.out.println("Bata-l");
        Optional<BikePark> bikeParkOptional = bikeParkRepository.findByConcurs_Id(idConcurs);
        if (!bikeParkOptional.isPresent()) {
            //throw new NotValidApplicantException("Applicant with ID:" + id + " doesn't exist!");
        }
        BikePark bikePark = bikeParkOptional.get();
        return bikePark;
    }

    @Override
    public Concurs getConcursById(Long idConcurs){
        return concursRepository.getOne(idConcurs);
    }

    @Override
    public Photo getConcursLogo(Long idConcurs) {
        Optional<BikePark> bikeParkOptional = bikeParkRepository.findByConcurs_Id(idConcurs);
        if (!bikeParkOptional.isPresent()) {
            System.out.println("Fraiere");
            //throw new NotValidApplicantException("Applicant with ID:" + id + " doesn't exist!");
        }
        BikePark bikePark = bikeParkOptional.get();
        System.out.println(bikePark.toString());
        Optional<Contact> contactOptional = contactRepository.findByBikePark_Id(bikePark.getId());
        if (!contactOptional.isPresent()) {
            System.out.println("Fraiere");
            //throw new NotValidApplicantException("Applicant with ID:" + id + " doesn't exist!");
        }
        Contact contact = contactOptional.get();
        System.out.println(contact.toString());
        Optional<Photo> photoOptional = photoRepository.findByContact_Id(contact.getId());
        if (!photoOptional.isPresent()) {
            System.out.println("Fraiere");
            //throw new NotValidApplicantException("Applicant with ID:" + id + " doesn't exist!");
        }
        Photo photo = photoOptional.get();
        System.out.println(photo.toString());
        return photo;
    }

    //TODO DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd

    @Override
    public Categorie deleteCategorie(Long id){
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        if (!categorieOptional.isPresent()) {
            //throw new NotValidApplicantException("Applicant with ID:" + id + " doesn't exist!");
        }
        Categorie categorie = categorieOptional.get();
        Optional<Concurs> concursOptional = concursRepository.findById(categorie.getConcurs().getId());
        Concurs concurs = concursOptional.get();
        concurs.removeCategorie(categorie);

        categorieRepository.delete(categorie);
        return categorie;
    }

    @Override
    public Traseu deleteTraseu(Long id){
        Optional<Traseu> traseuOptional = traseuRepository.findById(id);
        if (!traseuOptional.isPresent()) {
            //throw new NotValidApplicantException("Applicant with ID:" + id + " doesn't exist!");
        }
        Traseu traseu = traseuOptional.get();
        Optional<BikePark> bikeParkOptional = bikeParkRepository.findById(traseu.getBikePark().getId());
        BikePark bikePark = bikeParkOptional.get();
        bikePark.removeTraseu(traseu);

        traseuRepository.delete(traseu);
        return traseu;
    }

    private Concurs findConcursById(Long id) {
        Optional<Concurs> concursOptional = concursRepository.findById(id);
        if (!concursOptional.isPresent()) {
            //throw new NotValidCompanyException("Company with ID:" + id + " doesn't exist!");
        }

        Concurs concurs = concursOptional.get();
        System.out.println(concurs.getDenumire());
        return concurs;
    }

    private Traseu findTraseuById(Long id) {
        Optional<Traseu> traseuOptional = traseuRepository.findById(id);
        if (!traseuOptional.isPresent()) {
            //throw new NotValidCompanyException("Company with ID:" + id + " doesn't exist!");
        }

        Traseu traseu = traseuOptional.get();
        return traseu;
    }

    private BikePark findBikeparkById(Long id) throws NotValidBikeparkException {
        Optional<BikePark> bikePark = bikeParkRepository.findById(id);
        if (bikePark.isPresent()) {
            return bikePark.get();
        }
        throw new NotValidBikeparkException("No company with this user ID!");
        /*Optional<BikePark> bikeParkOptional = bikeParkRepository.findById(id);
        if (!bikeParkOptional.isPresent()) {
            //throw new NotValidCompanyException("Company with ID:" + id + " doesn't exist!");
        }

        BikePark bikePark1 = bikeParkOptional.get();
        System.out.println(bikePark1.getDenumire());
        return bikePark1;*/
    }

    private Contact findContactById(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (!contactOptional.isPresent()) {
            //throw new NotValidCompanyException("Company with ID:" + id + " doesn't exist!");
        }

        Contact contact = contactOptional.get();
        return contact;
    }

    @Override
    public List<Traseu> findTraseeByBikeparkId(Long id) {
        Optional<List<Traseu>> traseuList = traseuRepository.findAllByBikePark_Id(id);

        return traseuList.get();
    }

    @Override
    public RezervareConcurs createRezervareConcurs(Concurs concurs, RezervareConcurs rezervareConcurs) {
        Concurs actualConcurs = findConcursById(concurs.getId());

        actualConcurs.addRezervareConcurs(rezervareConcurs);
        return rezervareConcursRepository.saveAndFlush(rezervareConcurs);


    }

    @Override
    public Categorie createCategorie(Concurs concurs, Categorie categorie) {
        Concurs actualConcurs = findConcursById(concurs.getId());

        actualConcurs.addCategorii(categorie);
        return categorieRepository.saveAndFlush(categorie);

    }

    @Override
    public Traseu createTraseu(BikePark bikePark, Traseu traseu) throws NotValidBikeparkException {
        BikePark actualBikepark = findBikeparkById(bikePark.getId());

        actualBikepark.addTraseu(traseu);
        return traseuRepository.saveAndFlush(traseu);

    }

    @Override
    public RezervareBikePark createRezervareBikepark(BikePark bikePark, RezervareBikePark rezervareBikePark) throws NotValidBikeparkException {
        System.out.println(bikePark.getId());
        BikePark actualBikepark = getBikeparkById(bikePark.getId());

        actualBikepark.addRezervareBikeParks(rezervareBikePark);
        return rezervareBikeParkRepository.saveAndFlush(rezervareBikePark);

    }

    @Override
    public BikePark updateBikepark(Long id, BikePark bikePark) throws NotValidBikeparkException {

        BikePark currentBikepark = findBikeparkById(id);

        updateBikeparkFields(currentBikepark, bikePark);

        return bikeParkRepository.save(currentBikepark);
    }

    @Override
    public Contact updateBikeparkContact(Long id, Contact contact) {

        Contact currentContact = findContactById(id);

        updateBikeparkContactFields(currentContact, contact);

        return contactRepository.save(currentContact);
    }
}
