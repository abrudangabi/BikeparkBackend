package com.gabi.backend.bikeparkend.service.implementation;

import com.gabi.backend.bikeparkend.exceptions.NotValidBikeparkException;
import com.gabi.backend.bikeparkend.exceptions.NotValidBikerException;
import com.gabi.backend.bikeparkend.exceptions.RecommendationException;
import com.gabi.backend.bikeparkend.model.*;
import com.gabi.backend.bikeparkend.repository.*;
import com.gabi.backend.bikeparkend.repository.BikerRepository;
import com.gabi.backend.bikeparkend.service.GenericService;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class GenericServiceImpl implements GenericService {

    private static final int DEFAULT_LIMIT = 10;

    /*@Autowired
    private DataSource dataSource;*/

    @Autowired
    private ItemBasedRecommender recommender;

    /*@Autowired
    private AdministratorRepository administratorRepository;*/

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

    @Autowired
    private PreferintaRepository preferintaRepository;

    @Autowired
    private SimilaritateRepository similaritateRepository;

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
        throw new NotValidBikeparkException("No company with this user ID!" + id);
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
    public Biker findBikerById(Long id) throws NotValidBikerException {
        Optional<Biker> contactOptional = bikerRepository.findById(id);
        if (!contactOptional.isPresent()) {
            throw new NotValidBikerException("Biker with ID:" + id + " doesn't exist!");
        }

        Biker contact = contactOptional.get();
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

        //traseu.setDificultate(Dificultate.usor);
        actualBikepark.addTraseu(traseu);
        return traseuRepository.saveAndFlush(traseu);

    }

    @Override
    public RezervareBikePark createRezervareBikepark(BikePark bikePark, RezervareBikePark rezervareBikePark) throws NotValidBikeparkException, NotValidBikerException {
        /*System.out.println("Id Bikepark "+bikePark.getId());
        System.out.println("Ziua rezervare "+rezervareBikePark.getZiua());
        System.out.println("Id rezervare " + rezervareBikePark.getId());*/
        BikePark actualBikepark = getBikeparkById(bikePark.getId());
        Biker actualBiker = findBikerById((long)1);
        //Rezervarea deja e construita; mai trebuie legata de bikepark si de biker

        actualBiker.addRezervareBikeParks(rezervareBikePark);
        actualBikepark.addRezervareBikeParks(rezervareBikePark);
        RezervareBikePark rezervareBikeParkFinal = rezervareBikeParkRepository.saveAndFlush(rezervareBikePark);
        //System.out.println("Id nou rezervare "+rezervareBikeParkFinal.getId());
        //TODO CALCULARE PREFERINTA PT BIKER
        //TODO SE FACE DUPA NR DE REZERVARI
        /*boolean da = false;
        if (da){
            System.out.println("Intra in setarea de Preferinte");
            int nrRezervari = 0;
            //int nrRezervari = actualBiker.getRezervareBikeParks().size();
            System.out.println(nrRezervari);
            Preferinte preferinte = new Preferinte();
            preferinte.setPreference(nrRezervari);
            actualBikepark.addPreferinta(preferinte);
            actualBiker.addPreferinta(preferinte);
            System.out.println("Preferinta "+preferinte.getUser_id()+" "+preferinte.getItem_id()+" "+preferinte.getPreference());
            //Se salveaza Preferinta
        }*/
        return rezervareBikeParkFinal;
        //return rezervareBikeParkRepository.saveAndFlush(rezervareBikePark);

    }

    //TODO FUNCTIONAL
    public void resetarePreferinte(){
        System.out.println("Da-i preferinta");
        for(Biker biker : this.bikerRepository.findAll()){
            //
            for(BikePark bikePark : this.bikeParkRepository.findAll()){
                int nrRezervari = 0;
                double pref = 1;
                nrRezervari = biker.numarRezervate(bikePark);
                //System.out.println("Cate gaseste "+biker.getPrenume()+ " " + nrRezervari + " " + bikePark.getDenumire());
                //todo
                if(nrRezervari == 0){
                    pref = 1;
                }
                if(nrRezervari == 1){
                    pref = 2;
                }
                if(nrRezervari == 2){
                    pref = 3;
                }
                if(nrRezervari == 3){
                    pref = 3;
                }
                if(nrRezervari == 4){
                    pref = 4;
                }
                if(nrRezervari == 5){
                    pref = 4;
                }
                if(nrRezervari > 5){
                    pref = 5;
                }
                /*else{
                    pref = nrRezervari;
                }*/
                Preferinte preferinte = new Preferinte();
                preferinte.setPreference(pref);
                preferinte.setItem_id(bikePark);
                preferinte.setUser_id(biker);
                biker.addPreferinta(preferinte);
                bikePark.addPreferinta(preferinte);
                //
            }
        }
        saveInPreferinte();
    }

    public void saveInPreferinte(){
        List<Preferinte> preferinteList = preferintaRepository.findAll();

        String filename = "C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\preferinte.csv";
        try(PrintWriter pw = new PrintWriter(filename)){
            for(Preferinte p : preferinteList){
                String line = ""+p.getUser_id().getId()+ ","+
                        p.getItem_id().getId()+ ","+
                        (double)p.getPreference()+ "\n";
                pw.write(line);
            }
        }catch(IOException e){
            System.err.println(e);
        }
    }

    public void salveazaInBD(){
        List<Preferinte> preferaList = new ArrayList<>();
        try {
            String filename = "C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\ratings2.txt";

            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            long id = 1;
            System.out.println("Preferinte BD si id " + id);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Preferinte prefera = new Preferinte();//new Prefera(Long.parseLong(values[0]), Long.parseLong(values[1]), Double.parseDouble(values[2]));
                //
                long id_user = Long.parseLong(values[0]);
                long id_item = Long.parseLong(values[1]);
                double preferinta = Double.parseDouble(values[2]);
                prefera.setId(id);
                Biker biker;
                biker= findBikerById(id_user);
                prefera.setUser_id(biker);
                BikePark bikePark;
                bikePark = findBikeparkById(id_item);
                prefera.setItem_id(bikePark);
                prefera.setPreference(preferinta);

                biker.addPreferinta(prefera);
                bikePark.addPreferinta(prefera);
                preferaList.add(prefera);
                //preferintaRepository.save(prefera);
                System.out.println("Da id " + id);
                id++;
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotValidBikeparkException e) {
            e.printStackTrace();
        } catch (NotValidBikerException e) {
            e.printStackTrace();
        }
        System.out.println("A salvat " + preferaList.size());
    }

    public List<BikePark> getBikeparkRecomandate(List<RecommendedItem> recommendedItems){
        List<Long> idBikepark = new ArrayList<>();
        List<BikePark> bikeParks = new ArrayList<>();
        for(RecommendedItem item : recommendedItems){
            idBikepark.add(item.getItemID());
        }

        for(Long id : idBikepark){
            try {
                bikeParks.add(getBikeparkById(id));
            } catch (NotValidBikeparkException e) {
                e.printStackTrace();
            }
        }

        return bikeParks;
    }

    public List<RecommendedItem> recomandaLista(Integer idUser){
        List<RecommendedItem> itemRecommendations = null;
        try {
            DataModel dataModel = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\prefera.txt"));

            ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
            Recommender itemRecommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
            itemRecommendations = itemRecommender.recommend(idUser, DEFAULT_LIMIT);
            /*int pas = 0;
            for (RecommendedItem itemRecommendation : itemRecommendations) {
                System.out.println("Item: " + itemRecommendation);
                pas++;
            }
            System.out.println(pas);*/
        }catch (Exception e) {
            e.printStackTrace();
        }

        return itemRecommendations;
    }

    public List<Prefera> loadPreferinte(){
        List<Prefera> preferaList = new ArrayList<>();
        try {
            String filename = "C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\prefera.txt";

            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Prefera elev = new Prefera(Long.parseLong(values[0]), Long.parseLong(values[1]), Double.parseDouble(values[2]));
                preferaList.add(elev);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Cate preferinte sunt " + preferaList.size());
        return preferaList;
    }

    public void citestePreferinte(){
        List<Prefera> preferaList = new ArrayList<>();
        try {
            String filename = "C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\ratings2.txt";

            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Prefera elev = new Prefera(Long.parseLong(values[0]), Long.parseLong(values[1]), Double.parseDouble(values[2]));
                preferaList.add(elev);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Cate preferinte sunt " + preferaList.size());
        preferaList.sort(Comparator.comparing(Prefera::getItem_id).reversed());
        for(Prefera p : preferaList){
            System.out.println(p.getItem_id() + " " + p.getUser_id() + " " + p.getPreference());
        }
        List<Long> useri = new ArrayList<>();
        List<Long> itemi = new ArrayList<>();
        for(Prefera p : preferaList){
            itemi.add(p.getItem_id());
        }
        preferaList.sort(Comparator.comparing(Prefera::getUser_id).reversed());
        for(Prefera p : preferaList){
            useri.add(p.getUser_id());
        }
        List<Long> newListUseri = useri.stream()
                .distinct()
                .collect(Collectors.toList());
        List<Long> newListItemi = itemi.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Itemi " + newListItemi.size());
        System.out.println("Useri " + newListUseri.size());

        //TODO 2071 Itemi
        //TODO 1508 Useri
        //TODO 35497 Preferinte totale

    }

    /*public void citestePreferinte(){
        List<Prefera> preferaList = new ArrayList<>();
        try {
            String filename = "C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\ratings2.txt";

            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Prefera elev = new Prefera(Long.parseLong(values[0]), Long.parseLong(values[1]), Double.parseDouble(values[2]));
                preferaList.add(elev);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        preferaList.sort(Comparator.comparing(Prefera::getItem_id).reversed());
        for(Prefera p : preferaList){
            System.out.println(p.getItem_id() + " " + p.getUser_id() + " " + p.getPreference());
        }
        List<Long> useri = new ArrayList<>();
        List<Long> itemi = new ArrayList<>();
        for(Prefera p : preferaList){
            itemi.add(p.getItem_id());
        }
        preferaList.sort(Comparator.comparing(Prefera::getUser_id).reversed());
        for(Prefera p : preferaList){
            useri.add(p.getUser_id());
        }
        List<Long> newListUseri = useri.stream()
                .distinct()
                .collect(Collectors.toList());
        List<Long> newListItemi = itemi.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Itemi " + newListItemi.size());
        System.out.println("Useri " + newListUseri.size());

    }*/

    public List<BikePark> recomanda(Biker biker) {
        //TODO GENERARE
        //salveazaInBD();
        //generare();
        //citestePreferinte();
        //saveInPreferinte();
        System.out.println();
        System.out.println();
        /*preferintaRepository.deleteAll();;
        similaritateRepository.deleteAll();*/
        /*for(Biker b : bikerRepository.findAll()){
            b.setPreferinte(new HashSet<>());
        }
        for(BikePark b : bikeParkRepository.findAll()){
            b.setSimilaritati1(new HashSet<>());
            b.setPreferinte(new HashSet<>());
        }*/
        /*preferintaRepository.deleteAll();;
        similaritateRepository.deleteAll();*/
        /*curataSimilaritati();
        curataPreferinte();*/
        System.out.println("Cate preferinte " + preferintaRepository.findAll().size());
        /*System.out.println("Cate preferinte " + preferintaRepository.findByIdEquals((long)1).size());
        System.out.println("Cate preferinte " + preferintaRepository.findByIdEquals((long)1).size());*/
        //System.out.println("Cate preferinte " + preferintaRepository.findByPreference(3.5).size());

        boolean merge = true;
        if (merge) {
            System.out.println("MYSQL Recomandare");
            try {
                MysqlDataSource dataSource = new MysqlDataSource();
                ConnectionPoolDataSource connectionPoolDataSource = new MysqlConnectionPoolDataSource();
                //dataSource.setServerName("localhost");
                //dataSource.setPort(3306);
                //dataSource.setPortNumber(3306);
                dataSource.setUser("gabi");
                dataSource.setPassword("gabi");
                dataSource.setAutoReconnectForConnectionPools(true);
                //dataSource.setDatabaseName("bikepark");
                dataSource.setURL("jdbc:mysql://localhost:3306/bikepark?useSSL=false");
                JDBCDataModel dm = new MySQLJDBCDataModel(dataSource, "taste_preferences", "user_id", "item_id", "preference", "timestamp");

                DataModel model = dm;

                ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(model);
                Recommender itemRecommender = new GenericItemBasedRecommender(model, itemSimilarity);
                List<RecommendedItem> itemRecommendations = itemRecommender.recommend(biker.getId(), 20);
                int pas = 0;
                for (RecommendedItem itemRecommendation : itemRecommendations) {
                    System.out.println("Item: " + itemRecommendation);
                    pas++;
                }
                System.out.println(pas);
                /*UserSimilarity similarity = new PearsonCorrelationSimilarity(dm);
                UserNeighborhood neighbor = new NearestNUserNeighborhood(2, similarity, dm);
                Recommender recommender = new GenericUserBasedRecommender(dm, neighbor, similarity);
                List<RecommendedItem> list = recommender.recommend(1, 3);// recommend*/
                // one item
                // to user
                // 1
                System.out.println("Intra pana aici recomandarea MYSQL");
                for (RecommendedItem ri : itemRecommendations) {
                    System.out.println(ri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*try {
            DataModel dataModelF = new MySQLJDBCDataModel();
        } catch (TasteException e) {
            e.printStackTrace();
        }*/

        boolean intra = false;

        if(intra) {
            try {
                //TODO DA DUPA VECINI, NU SE MERITA
                DataModel dataModel = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\ratings2.txt"));
                //DataModel dataModel = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\gunoi.csv"));
                //DataModel dataModel = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\preferinte.csv"));
                //ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
                UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(20, similarity, dataModel);
                Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
                List<RecommendedItem> recommendations = recommender.recommend(biker.getId(), 20);
                int o=0;
                for (RecommendedItem recommendation : recommendations) {
                    System.out.println(recommendation);
                    o++;
                }
                System.out.println("pasi idioti "+o);
                System.out.println("++++++++++++++");
                //TODO CEA MAI BUNA RECOMANDARE
                ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
                Recommender itemRecommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
                List<RecommendedItem> itemRecommendations = itemRecommender.recommend(biker.getId(), 20);
                int pas = 0;
                for (RecommendedItem itemRecommendation : itemRecommendations) {
                    System.out.println("Item: " + itemRecommendation);
                    pas++;
                }
                System.out.println(pas);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("------------------------------");
        List<BikePark> bikeParks = new ArrayList<>();
        if(intra) {
            try {

                //TODO PICA DIN CAUZA NUMARULUI
                System.out.println("Intra");
                //DataModel model = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\preferinte.csv"));
                //DataModel model = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\Sisteme_Recomandare\\src\\main\\resources\\MDist1.csv"));
                //DataModel model = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\review-ratings.txt"));
                DataModel model = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\ratings2.txt"));
                //DataModel model = new FileDataModel(new File("C:\\Users\\Gabi\\IdeaProjects\\ProiectBikePark_Final\\src\\main\\resources\\gunoi.csv"));
                CityBlockSimilarity similarity = new CityBlockSimilarity(model);
                UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
                UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

                // The First argument is the userID and the Second parameter is 'HOW MANY'

                int pas = 0;
                List<RecommendedItem> recommendations2 = recommender.recommend(biker.getId(), 2 * DEFAULT_LIMIT);

                for (RecommendedItem recommendation : recommendations2) {
                    System.out.println(recommendation);
                    pas++;
                }
                System.out.println(pas);
            } catch (Exception e) {
                System.err.println("Exception occured !");
            }
        }
        return bikeParks;
    }

    public void generare(){
        //Generare Bikeri, Useri, Contact, Roluri, Disciplina, Locatie, Foto
        boolean bikerIF = false;

        int nr = 1;
        if(bikerIF) {
            for (int i = 1; i <= 1499; i++) {
                //TODO 1507 useri de adaugat
                //nr++;
            //int i = 1;

                //System.out.println("ID user final " + nr);

                //TODO BIKER
                //if(bikerIF) {
                int nrUser = 1 + i;
                long nrBiker = 9 + i;
                int idUser = 15 + i;
                long userSmecher = (long) idUser;
                //User
                User user = new User();
                user.setId(idUser);
                user.setActive(false);
                user.setUsername("user" + nrUser);
                user.setPassword("pass" + nrUser);
                user.setEmail("user" + nrUser + "@yahoo.com");

                //TODO ID PT BIKER
                Long idBiker = (long) nrBiker;
                //Date date = new Date();
                //Biker
                Biker biker = new Biker();
                biker.setId(idBiker);
                LocalDate localDate1 = LocalDate.of(2016, Month.FEBRUARY, 3);
                LocalDate localDate2 = LocalDate.of(1997, Month.MAY, 15);
        /*DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try {
            LocalDate dateStart = format.parse("1997-05-07");
            biker.setDataNasterii(dateStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
                biker.setNume("biker" + nrUser);
                biker.setPrenume("biker" + nrUser);
                biker.setBicicleta("Canyon");
                biker.setAniExperienta(2);
                biker.setDataNasterii(localDate2);
                biker.setMembruData(localDate1);
                biker.setDisciplinaFavorita(Disciplina.ENDURO);

                biker.setUser(user);
                user.setBiker(biker);

                //Role
                Role role = new Role();
                role.setRoleId(2);
                role.setRoleString(RoleString.BIKER);
                user.addRole(role);

                //Contact
                Contact contact = new Contact();
                contact.setId((long)idUser);
                contact.setPhoneNumber("0770123456");
                contact.setFacebookLink("https://www.facebook.com/dannyhartfanpage/");

                contact.setBiker(biker);
                biker.setContact(contact);

                //Locatie
                Locatie locatie = new Locatie();
                locatie.setId(idUser);
                locatie.setTara("Romania");
                locatie.setProvincie("Cluj");
                locatie.setLocalitate("Clij-Napoca");
                locatie.setLatitude(45.0);
                locatie.setLongitude(25.0);
                locatie.setCodPostal("400000");
                locatie.setStrada("Constantin Brancusi");
                locatie.setNumber("120");

                locatie.setContact(contact);
                contact.setLocatie(locatie);

                //Photo
                Photo photo = new Photo();
                Long idPhoto = (long) idUser;
                photo.setId(idPhoto);
                photo.setUrl("https://www.ucc-sportevent.com/wp-content/uploads/photo-Mega-Dimanche-Cyril-Charpin-9-1080x675.jpg");
                //photo.setUrl("https://www.google.ro/imgres?imgurl=https%3A%2F%2Fi.ytimg.com%2Fvi%2Fm2gwFkCU1Q4%2Fmaxresdefault.jpg&imgrefurl=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3Dm2gwFkCU1Q4&docid=vJ3OLal0dIKuRM&tbnid=DSwEPokC_-p--M%3A&vet=10ahUKEwiOmI3QtuniAhUB1BoKHc5TDtAQMwhAKAAwAA..i&w=1280&h=720&safe=off&bih=654&biw=1366&q=megavalanche&ved=0ahUKEwiOmI3QtuniAhUB1BoKHc5TDtAQMwhAKAAwAA&iact=mrc&uact=8");

                photo.setContact(contact);
                contact.setPhoto(photo);

                //Repository
                userRepository.save(user);
                bikerRepository.save(biker);
                roleRepository.save(role);
                contactRepository.save(contact);
                locatieRepository.save(locatie);
                photoRepository.save(photo);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nr = i;
            }
        }
        System.out.println("A adaugat " + nr + " bikeri");

        //Generare Bikepark, Useri, Contact, Roluri, Disciplina, Locatie, Dificultate, Foto, 1 Traseu
        boolean ifBikepark = false;

        if(ifBikepark) {
            //for (int i = 2; i <= 2064; i++) {
                //TODO
            int i = 2065;

                //TODO BIKEPARK
                //if(ifBikepark){
                int nrUser = 1500 + i;
                int idUser = 1514 + i;
                long nrBikepark = 6 + i;
                long userSmecher = (long) idUser;
                //User
                User user = new User();
                user.setId(idUser);
                user.setActive(false);
                user.setUsername("user" + nrUser);
                user.setPassword("pass" + nrUser);
                user.setEmail("user" + nrUser + "@yahoo.com");

                //TODO ID PT BIKEPARK
                Long idBikepark = (long) nrBikepark;
                //Bikepark
                BikePark bikePark = new BikePark();
                bikePark.setDenumire("Bikepark " + nrUser);
                bikePark.setNrLocuri(100);
                bikePark.setDescriere("Bikepark care e ideal relaxarii cu bicicleta");
                bikePark.setId(idBikepark);
                bikePark.setTelescaun(true);

                bikePark.setUser(user);
                user.setBikePark(bikePark);

                //Role
                Role role = new Role();
                role.setRoleId(1);
                role.setRoleString(RoleString.BIKEPARK);
                user.addRole(role);

            /*select contact_id, biker_id, bikepark_id from contact
            where contact_id > 1400*/

                //Contact
                Contact contact = new Contact();
                contact.setId((long)idUser);
                contact.setPhoneNumber("0770123456");
                contact.setFacebookLink("https://www.facebook.com/WhistlerBikePark/");
                contact.setWebsite("https://www.whistlerblackcomb.com/plan-your-trip/lift-access/bike-park-passes.aspx");
                //contact.setWebsite("https://www.whistlerblackcomb.com/explore-the-resort/activities-and-events/whistler-mountain-bike-park/whistler-mountain-bike-park.aspx#/");

                contact.setBikePark(bikePark);
                bikePark.setContact(contact);

                //Locatie
                Locatie locatie = new Locatie();
                locatie.setId(idUser);
                locatie.setTara("Canada");
                locatie.setProvincie("British Columbia");
                locatie.setLocalitate("Whistler");
                locatie.setLatitude(50.0);
                locatie.setLongitude(123.0);
                locatie.setCodPostal("400000");
                locatie.setStrada("Mountain Square");
                locatie.setNumber("4282 ");

                locatie.setContact(contact);
                contact.setLocatie(locatie);

                //Photo
                Photo photo = new Photo();
                Long idPhoto = (long) idUser;
                photo.setId(idPhoto);
                photo.setUrl("https://lh5.googleusercontent.com/p/AF1QipPNXft_3I65CozWF08kgIsKJCERDLdpqQopDlYg=w408-h306-k-no");

                photo.setContact(contact);
                contact.setPhoto(photo);

                //Traseu
                long idTraseu = idBikepark + 2;
                Traseu traseu = new Traseu();
                traseu.setId(idTraseu);
                traseu.setLungime((long) 1200);
                traseu.setDenumire("Flow Line");
                traseu.setTipTraseu(Disciplina.ENDURO);
                traseu.setDificultate(Dificultate.mediu);

                bikePark.addTraseu(traseu);

                //Repository
                userRepository.save(user);
                bikeParkRepository.save(bikePark);
                roleRepository.save(role);
                contactRepository.save(contact);
                locatieRepository.save(locatie);
                photoRepository.save(photo);
                traseuRepository.save(traseu);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nr = i;
            //}
        }
        System.out.println("A adaugat " + nr + " bikepark-uri");

        System.out.println("AM SALVAT IN BD");


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

    //TODO RECOMANDARE

    @Override
    public void verifBD(Biker biker){
        //Bikepark 1
        BikePark bikePark1 = bikeParkRepository.getOne((long)1);
        //Bikepark 2
        BikePark bikePark2 = bikeParkRepository.getOne((long)2);
        Preferinte preferinte = new Preferinte();
        preferinte.setPreference(1.0);
        //preferinte.setUser_id(user);
        //preferinte.setItem_id(bikePark);

        // Se adauga preferinta pt Bikepark 1 si Biker 1
        biker.addPreferinta(preferinte);
        bikePark1.addPreferinta(preferinte);
        /*Set<Preferinte> preferinteSet = new HashSet<>();
        preferinteSet.add(preferinte);
        biker.setPreferinte(preferinteSet);*/
        preferintaRepository.saveAndFlush(preferinte);
        System.out.println("A salvat preferinta");

        Similaritati similaritati = new Similaritati();
        // Se face similaritate intre Bikepark 1 si Bikepark 2
        similaritati.setSimilarity((double)1);
        bikePark1.addSimilarite_A(similaritati);
        //bikePark2.addSimilarite_B(similaritati);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BikePark> recommend(Biker user, int howMany) {
        BikePark bikePark = bikeParkRepository.getOne((long)1);
       /* Preferinte preferinte = new Preferinte();
        preferinte.setPreference(1);
        //preferinte.setUser_id(user);
        preferinte.setItem_id(bikePark);
        user.addPreferinta(preferinte);
       *//* Set<Preferinte> preferinteSet = new HashSet<>();
        preferinteSet.add(preferinte);
        user.setPreferinte(preferinteSet);*//*
        preferintaRepository.saveAndFlush(preferinte);*/

        //TODO SUNT PROST, ACUM VAD CA E TRANSACTIONAL

        /*Traseu traseu = new Traseu();
        traseu.setDenumire("Rahat");
        traseu.setDificultate("Greu");
        traseu.setLungime((long)1200);
        traseu.setTipTraseu("XC");
        bikePark.addTraseu(traseu);
        traseuRepository.saveAndFlush(traseu);*/
        //calculateSimilarities();

        System.out.println(user.getPrenume());
        System.out.println(howMany);
        if (howMany <= 0) {
            howMany = DEFAULT_LIMIT;
        }
        System.out.println(howMany);
        List<BikePark> recomandate = getRecommendedMovies(getItems(user.getId(), howMany));

        return recomandate;
        //return getRecommendedMovies(getItems(user.getId(), howMany));
    }

    private List<BikePark> getRecommendedMovies(final List<RecommendedItem> items) {
        final List<BikePark> movies = new ArrayList<>();
        int count = 0;
        System.out.println("Face recomandare");
        for (final RecommendedItem item : items) {
            System.out.println("Intra la recomandari");
            System.out.println(item.getItemID() + " " + item.getValue());
            Optional<BikePark> movieOptional = bikeParkRepository.findById(item.getItemID());
            BikePark movie = movieOptional.get();
            //movie.setRank(++count);
            movies.add(movie);
            //recommender.mostSimilarItems(1,2);
        }

        return movies;
    }

    private List<RecommendedItem> getItems(final Long userId, final int howMany) {
        List<RecommendedItem> items = null;
        try {
            items = recommender.recommend(userId, howMany);
            //items = null; //recommender.recommend(userId, howMany);
        } catch (Exception e) { //TasteException
            throw new RecommendationException("Unable to make recommendation for userId: " + userId);
        }
        //calculateSimilarities();

        return items;
    }

    public Preferinte findByBikerAndBikepark(Biker biker,BikePark bikePark){
        List<Preferinte> preferinteList = preferintaRepository.findAll();
        Preferinte preferinte = null;
        for(Preferinte p : preferinteList){
            if(p.getUser_id().equals(biker) && p.getItem_id().equals(bikePark))
                preferinte = p;
        }
        return preferinte;
    }

    public MatrixSimilarity determinareSimilaritati(FirstMatrix firstMatrix){
        int nrOfItems = firstMatrix.getColumns();
        MatrixSimilarity matrice = new MatrixSimilarity(nrOfItems,nrOfItems);
        int index = 0;
        double valoare = 1.0;
        double vector1[] = new double[firstMatrix.getRows()];
        double vector2[] = new double[firstMatrix.getRows()];
        double vector3[] ;
        double vector4[] ;
        for(int i = 0; i < firstMatrix.getColumns() - 1; i++){
            for(int j = i + 1; j <= firstMatrix.getColumns() - 1; j++){
                System.out.println(i + " " + j);
                for(int k = 0; k <= firstMatrix.getRows() - 1; k++){
                    //TODO AICI DEPINDE CE VREAU SA IAU
                    //TODO DACA E BUN SI PREFERINTA 0
                    if(firstMatrix.getValue(k,i) >= 0 && firstMatrix.getValue(k,j) >= 0) {
                        vector1[index] = firstMatrix.getValue(k, i);
                        vector2[index] = firstMatrix.getValue(k, j);
                        //TODO Calcul intre Bikepark-uri
                        //valoare = Cosinend.similarity(vector1,vector2);
                        index++;
                    }
                }
                System.out.println("Mortii ma-tii " + index);
                vector3 = new double[index];
                vector4 = new double[index];
                for(int h = 0; h <= index - 1; h++) {
                    vector3[h] = vector1[h];
                    vector4[h] = vector2[h];
                }
                /*for(int h = 0; h <= firstMatrix.getRows() - 1; h++) {
                    System.out.print(vector1[h] + " " + vector2[h]);
                    System.out.println();
                }*/
                index = 0;
                valoare = Cosinend.similarity(vector3,vector4);
                matrice.assignElement(valoare,i,j);
                matrice.assignElement(valoare,j,i);
            }
        }

        return matrice;
    }

    //TODO FUNCTIE CARE CALCULEAZA COS DE SIMILARITATE  ===> FOLOSITA DUPA O ADAUGARE DE REZERVARE
    //TODO TREBUIE CALCULAT SIMILARITATEA DINTRE ITEME
    public void calculateSimilarities(){
        //Creare Matrice de Similaritati
        List<Biker> bikers = bikerRepository.findAll();
        List<BikePark> bikeParks = bikeParkRepository.findAll();
        int nrBikers = bikers.size();
        int nrBikePark = bikeParks.size();

        //Matrice de useri x itemi
        FirstMatrix firstMatrix = new FirstMatrix(nrBikers,nrBikePark);
        int ordI=0, ordJ=0, maxI=0, maxJ=0;
        HashMap<Integer, Biker> hmap = new HashMap<Integer, Biker>();
        HashMap<Integer, BikePark> hmap2 = new HashMap<Integer, BikePark>();
        for(Biker b : bikers){
            hmap.put(ordI,b);
            ordI++;
        }
        //maxI=ordI-1;
        for(BikePark bp : bikeParks){
            hmap2.put(ordJ,bp);
            ordJ++;
        }
        //maxJ=ordJ-1;
        for(int i = 0; i <= nrBikers - 1; i++){
            for(int j = 0; j <= nrBikePark - 1; j++){
                Biker b = hmap.get(i);
                BikePark bp = hmap2.get(j);
                Preferinte preferinte = findByBikerAndBikepark(b,bp);
                /*Optional<Preferinte> preferinte = preferintaRepository.findByUser_idAndItem_id(b.getId(),bp.getId());
                Preferinte pfin = preferinte.get();*/
                double preferinta = 0;//preferinte.getPreference();
                if (preferinte != null){
                    preferinta = preferinte.getPreference();
                }
                firstMatrix.assignElement(preferinta,i,j);
            }
        }

        //todo se sterge tabelul din bd cu similaritati
        /*for(Similaritati s : similaritateRepository.findAll()){
            similaritateRepository.delete(s);
            System.out.println("Sterge-l");
        }*/
        //similaritateRepository.deleteAll();

        /*FirstMatrix test = new FirstMatrix(4,3);
        test.assignElement(2,0,0);test.assignElement(-1,0,1);test.assignElement(3,0,2);
        test.assignElement(5,1,0);test.assignElement(2,1,1);test.assignElement(-1,1,2);
        test.assignElement(3,2,0);test.assignElement(3,2,1);test.assignElement(1,2,2);
        test.assignElement(-1,3,0);test.assignElement(2,3,1);test.assignElement(2,3,2);
        test.getValue(0,0);
        System.out.println(test.toString());
        MatrixSimilarity similarity = determinareSimilaritati(test);*/
        System.out.println(firstMatrix.toString());
        MatrixSimilarity similarity = determinareSimilaritati(firstMatrix);
        similarity.afisare();
        //similarity = determinareSimilaritati(firstMatrix);

        for(int i = 0; i < similarity.getColumns() - 1; i++){
            for(int j = i + 1; j <= similarity.getColumns() - 1; j++){
                System.out.println(i + " " + j + " " + similarity.getElement(i,j));
                BikePark a = hmap2.get(i);
                BikePark b = hmap2.get(j);
                System.out.println("Bikepark "+a.getId()+" "+b.getId()+" "+similarity.getElement(i,j));
            }
        }

        //TODO COD EXPERIMENTAL PT SALVARE IN BD
        boolean da = false;
        int pas = 1;
        if(da){
            for(int i = 0; i < similarity.getColumns() - 1; i++){
                for(int j = i + 1; j <= similarity.getColumns() - 1; j++){
                    BikePark a = hmap2.get(i);
                    BikePark b = hmap2.get(j);
                    //BikeParkB b1 = b.toBikeparkB();
                    double sim = similarity.getElement(i,j);
                    Similaritati similaritati = new Similaritati();
                    similaritati.setSimilarity(sim);
                    Similaritati similaritati2 = new Similaritati();
                    similaritati2.setSimilarity(sim);

                    //todo
                    List<Similaritati> sm1 = new ArrayList<>(a.getSimilaritati1());
                    List<Similaritati> sm2 = new ArrayList<>(b.getSimilaritati1());

                    similaritati.setItem_id_a(a);
                    similaritati.setItem_id_b(b);
                    similaritati2.setItem_id_a(b);
                    similaritati2.setItem_id_b(a);
                    int ok = 0;
                    for(Similaritati s : sm1){
                        if(s.getItem_id_a().equals(similaritati.getItem_id_a()) && s.getItem_id_b().equals(similaritati.getItem_id_b())){
                            ok = 1;
                        }
                    }
                    if(ok == 0){
                        sm1.add(similaritati);
                        sm2.add(similaritati2);
                    }
                    else {
                        for (Similaritati s : sm1) {
                            if (s.getItem_id_a().equals(similaritati.getItem_id_a()) && s.getItem_id_b().equals(similaritati.getItem_id_b())) {
                                s.setSimilarity(similaritati.getSimilarity());
                            }
                        }
                        for (Similaritati s : sm2) {
                            if (s.getItem_id_a().equals(similaritati2.getItem_id_a()) && s.getItem_id_b().equals(similaritati2.getItem_id_b())) {
                                s.setSimilarity(similaritati.getSimilarity());
                            }
                        }
                    }

                    Set<Similaritati> rez1 = new HashSet<>(sm1);
                    Set<Similaritati> rez2 = new HashSet<>(sm2);

                    a.setSimilaritati1(rez1);
                    b.setSimilaritati1(rez2);

                    /*similaritati.setItem_id_a(a);
                    similaritati.setItem_id_b(b);
                    a.addSimilarite_A(similaritati);
                    b.addSimilarite_B(similaritati);*/
                    //a.addSimilaritate_A_B(similaritati);
                    //System.out.println("Pas "+pas);
                    pas++;
                }
            }
        }

        /*Preferinte preferinte = new Preferinte();
        preferinte.setPreference(nrRezervari);
        preferinte.setItem_id(bikePark);
        preferinte.setUser_id(biker);
        biker.addPreferinta(preferinte);
        bikePark.addPreferinta(preferinte);*/

        /*FirstMatrix test2 = new FirstMatrix(4,3);
        test.assignElement(0,0,0);test.assignElement(0,0,1);test.assignElement(0,0,2);
        test.assignElement(0,1,0);test.assignElement(0,1,1);test.assignElement(0,1,2);
        test.assignElement(0,2,0);test.assignElement(0,2,1);test.assignElement(0,2,2);
        test.assignElement(0,3,0);test.assignElement(0,3,1);test.assignElement(0,3,2);
        MatrixSimilarity similarity2 = determinareSimilaritati(test);
        similarity2.afisare();*/
        /*System.out.println();
        System.out.println();
        System.out.println(firstMatrix.toString());
        for(int i = 0; i <= nrBikers - 1; i++){
            for(int j = 0; j <= nrBikePark - 1; j++){
                System.out.println(i+" "+j+" "+hmap.get(i).getId()+" "+hmap2.get(j).getId()+" "+firstMatrix.getValue(i,j));
            }
        }*/

        //Calculare cosinus pt fiecare item
        // cos(A,B) = ( A * B ) / ( |A| * |B| )
        // |A| = sqrt( a[0]^2 + a[1]^2 )
        //todo
        /*double dop=vec1[0]*vec2[0] +  vec1[1]*vec2[1];
        double mag1=Math.sqrt(Math.pow(vec1[0],2) + Math.pow(vec1[1],2));
        double mag2=Math.sqrt(Math.pow(vec2[0],2) + Math.pow(vec2[1],2));
        double csim=dop/ (mag1 * mag2);*/

        //Calculare similaritati intre Bikepark-urile utilizatorului
        /*List<Preferinte> preferinteList = new ArrayList<>(biker.getPreferinte());
        for (Preferinte p : preferinteList){
            BikePark bikePark = p.getItem_id();
            Math.sin(1);
        }*/
    }

    /*public void resetareSimilaritati(MatrixSimilarity similarity){
        for(int i = 0; i < similarity.getColumns() - 1; i++){
            for(int j = i + 1; j <= similarity.getColumns() - 1; j++){
                BikePark a = hmap2.get(i);
                BikePark b = hmap2.get(j);
                //BikeParkB b1 = b.toBikeparkB();
                double sim = similarity.getElement(i,j);
                Similaritati similaritati = new Similaritati();
                similaritati.setSimilarity(sim);
                similaritati.setItem_id_a(a);
                similaritati.setItem_id_b(b);
                a.addSimilarite_A(similaritati);
                b.addSimilarite_B(similaritati);
                //a.addSimilaritate_A_B(similaritati);
                //System.out.println("Pas "+pas);
                pas++;
            }
        }
    }*/

    public void curataSimilaritati(){
        for(Similaritati s : similaritateRepository.findAll()){
            similaritateRepository.delete(s);
            System.out.println("Sterge-l");
        }
    }

    public void curataPreferinte(){
        for(Preferinte s : preferintaRepository.findAll()){
            preferintaRepository.delete(s);
            System.out.println("Sterge-l");
        }
    }
}
