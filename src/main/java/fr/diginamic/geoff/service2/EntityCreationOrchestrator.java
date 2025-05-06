package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dto.FilmDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.List;

public class EntityCreationOrchestrator {
    private final List<FilmDTO> filmDTOList;
    private final String persistenceUnitName;
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityCreationOrchestrator.class);

    public EntityCreationOrchestrator(List<FilmDTO> filmDTOList, String persistenceUnitName) {
        this.filmDTOList = filmDTOList;
        this.persistenceUnitName = persistenceUnitName;
    }


    public void orchestrateEntityCreation() {

        LOGGER.info("Starting Entity creation process at {}", LocalTime.now());

        createPays();


        // TODO rest of the entities

        LOGGER.info("Entity creation process completed");
    }

    private void createPays(){
        ServiceCreationPays paysCreation = new ServiceCreationPays(filmDTOList, persistenceUnitName);
        try {
            LOGGER.info("Starting creation of Pays entities");
            paysCreation.createPays();

        } finally {
            paysCreation.close();
        }
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