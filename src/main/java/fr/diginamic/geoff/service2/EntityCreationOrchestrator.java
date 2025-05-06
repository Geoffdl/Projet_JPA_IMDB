package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dto.FilmDTO;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.List;

public class EntityCreationOrchestrator {
    private final EntityManager em;
    private final List<FilmDTO> filmDTOList;
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityCreationOrchestrator.class);


    public EntityCreationOrchestrator(List<FilmDTO> filmDTOList, EntityManager em) {
        this.filmDTOList = filmDTOList;
        this.em = em;
    }


    public void orchestrateEntityCreation() {

        LOGGER.info("Starting Entity creation process at {}", LocalTime.now());


//        createPays();
        createLieux();
        createLangues();
        createGenres();


        // TODO rest of the entities

        LOGGER.info("Entity creation process completed");
        em.close();

    }

    private void createLieux() {
        ServiceCreationLieu serviceCreationLieu = new ServiceCreationLieu(em,filmDTOList);
        LOGGER.info("Starting creation of Lieu entities");
        serviceCreationLieu.createLieu();
    }

    private void createGenres() {
        ServiceCreationGenre serviceCreationGenre = new ServiceCreationGenre(em,filmDTOList);
        LOGGER.info("Starting creation of Genre entities");
        serviceCreationGenre.createGenreFromFilmDTO();
    }

    private void createPays() {
        ServiceCreationPays paysCreation = new ServiceCreationPays(em, filmDTOList);

        LOGGER.info("Starting creation of Pays entities");
        paysCreation.createPays();


    }

    private void createLangues() {
        ServiceCreationLangue creationLangue = new ServiceCreationLangue(em, filmDTOList);

        LOGGER.info("Starting creation of Langue entities");
        creationLangue.createLangueFromFilmDTO();


    }
//    private void createLangues(){
//        // Create languages
//        ServiceCreationLangue langueCreation = new ServiceCreationLangue(filmDTOList, persistenceUnitName);
//        try {
//            LOGGER.info("Starting creation of Langue entities");
//            langueCreation.createLangues();
//        } finally {
//            langueCreation.close();
//        }
//    }
}