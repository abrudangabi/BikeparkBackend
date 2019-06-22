package com.gabi.backend.bikeparkend.service.implementation;

import com.gabi.backend.bikeparkend.exceptions.NotValidBikeparkException;
import com.gabi.backend.bikeparkend.exceptions.NotValidBikerException;
import com.gabi.backend.bikeparkend.model.*;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
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
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import javax.sql.ConnectionPoolDataSource;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class CodGunoi {

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
                //biker= findBikerById(id_user);
                //prefera.setUser_id(biker);
                BikePark bikePark;
                //bikePark = findBikeparkById(id_item);
                //prefera.setItem_id(bikePark);
                prefera.setPreference(preferinta);

                //biker.addPreferinta(prefera);
                //bikePark.addPreferinta(prefera);
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
        } /*catch (NotValidBikeparkException e) {
            e.printStackTrace();
        } catch (NotValidBikerException e) {
            e.printStackTrace();
        }*/
        System.out.println("A salvat " + preferaList.size());
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
        //    //    //System.out.println("Cate preferinte " + preferintaRepository.findAll().size());
        /*System.out.println("Cate preferinte " + preferintaRepository.findByIdEquals((long)1).size());
        System.out.println("Cate preferinte " + preferintaRepository.findByIdEquals((long)1).size());*/
        //System.out.println("Cate preferinte " + preferintaRepository.findByPreference(3.5).size());

        boolean merge = false;
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
                List<RecommendedItem> recommendations2 = recommender.recommend(biker.getId(), 2 * 10);

                for (RecommendedItem recommendation : recommendations2) {
                    System.out.println(recommendation);
                    pas++;
                }
                System.out.println(pas);
            } catch (Exception e) {
                System.err.println("Exception occured !");
            }
        }
        //todo bikeParks = getBikeparkRecomandate(recomandaLista(biker.getId()));
        System.out.println("Lungime bikepark recomandate " + bikeParks.size());

        return bikeParks;
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

    {
        //Creare Matrice de Similaritati
        List<Biker> bikers = new ArrayList<>(); //bikerRepository.findAll();
        List<BikePark> bikeParks = new ArrayList<>(); // bikeParkRepository.findAll();
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
                Preferinte preferinte = new Preferinte(); //findByBikerAndBikepark(b,bp);
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
                /*userRepository.save(user);
                bikerRepository.save(biker);
                roleRepository.save(role);
                contactRepository.save(contact);
                locatieRepository.save(locatie);
                photoRepository.save(photo);*/
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
            /*userRepository.save(user);
            bikeParkRepository.save(bikePark);
            roleRepository.save(role);
            contactRepository.save(contact);
            locatieRepository.save(locatie);
            photoRepository.save(photo);
            traseuRepository.save(traseu);*/
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

    /*public void curataSimilaritati(){
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
    }*/
}
