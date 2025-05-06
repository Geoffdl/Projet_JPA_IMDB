package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dao.LangueDao;
import fr.diginamic.geoff.dto.FilmDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServiceCreationLangue {
    private final EntityManager em;
    private final List<FilmDTO> filmDTOList;
    private final LangueDao langueDao;
    private final ServiceLangue serviceLangue;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceCreationLangue.class);

    public ServiceCreationLangue(EntityManager em, List<FilmDTO> filmDTOList) {
        this.em= em;
        this.filmDTOList = filmDTOList;
        this.langueDao = new LangueDao(em);
        this.serviceLangue= new ServiceLangue(langueDao);
    }

    public void createLangueFromFilmDTO(){

        EntityTransaction transaction = em.getTransaction();

        filmDTOList.stream().filter(f -> f.getLangue() != null).forEach(f -> {
            try {
                transaction.begin();
                serviceLangue.getOrCreateFromFilmDTO(f);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                LOGGER.warn("Couldn't create langue for {} : {}", f.getImdbId(), e.getMessage());

            }
        });

    };
}