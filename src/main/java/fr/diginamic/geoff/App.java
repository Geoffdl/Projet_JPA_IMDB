package fr.diginamic.geoff;

import fr.diginamic.geoff.service.BaseEntityCreationService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static final String FILMS_JSON = "data/films.json";
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");

    public static void main(String[] args) {


        BaseEntityCreationService creationService = new BaseEntityCreationService();
        creationService.generateBaseEntityObjects();


    }
}