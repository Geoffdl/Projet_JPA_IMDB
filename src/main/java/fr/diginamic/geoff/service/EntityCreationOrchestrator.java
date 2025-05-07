package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.List;

public class EntityCreationOrchestrator {
    private final EntityManagerFactory emf;
    private final List<FilmDTO> filmDTOList;
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityCreationOrchestrator.class);


    public EntityCreationOrchestrator(List<FilmDTO> filmDTOList, String persistenceunit) {
        this.filmDTOList = filmDTOList;
        this.emf = Persistence.createEntityManagerFactory(persistenceunit);
    }


    /**
     *
     */
    public void orchestrateEntityCreation() {
        LOGGER.info("Starting Entity creation process at {}", LocalTime.now());
        try {
            createFilms();
        } catch (Exception e) {
            LOGGER.warn("Error : {}", e.getMessage());
        } finally {
            emf.close();
        }
        LOGGER.info("Entity creation process completed");
    }

    /**
     *
     */
    private void createFilms() {
        EntityManager em = emf.createEntityManager();
        try {
            ServiceCreationFilm serviceCreationFilm = new ServiceCreationFilm(em, filmDTOList);
            LOGGER.info("Starting creation of Film entities");
            serviceCreationFilm.createFilm();
        } catch (Exception e) {
            LOGGER.warn("Error {}", e.getMessage());
        } finally {
            em.close();
        }

    }

}