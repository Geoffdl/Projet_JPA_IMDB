package fr.diginamic.geoff;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.parser.JsonParser;
import fr.diginamic.geoff.service2.EntityCreationOrchestrator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static final String FILMS_JSON = "data/films.json";
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");

    public static void main(String[] args) {


//        BaseEntityCreationService creationService = new BaseEntityCreationService();
//        creationService.generateBaseEntityObjects();

        JsonParser parser = new JsonParser();

        try (EntityManager em = emf.createEntityManager()){
            List<FilmDTO> filmDTOList = parser.tryReading(FilmDTO.class, FILMS_JSON);

            EntityCreationOrchestrator createEntities = new EntityCreationOrchestrator(filmDTOList, em);

            createEntities.orchestrateEntityCreation();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            emf.close();
        }


    }
}