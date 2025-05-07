package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dao.GenreDAO;
import fr.diginamic.geoff.dto.FilmDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServiceCreationGenre {
    private final EntityManager em;
    private final List<FilmDTO> filmDTOList;
    GenreDAO genreDAO;
    ServiceGenre serviceGenre;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceCreationGenre.class);

    public ServiceCreationGenre(EntityManager em, List<FilmDTO> filmDTOList) {
        this.em = em;
        this.filmDTOList = filmDTOList;
        this.genreDAO = new GenreDAO(em);
        this.serviceGenre = new ServiceGenre(genreDAO);
    }

    public void createGenreFromFilmDTO() {
        for (FilmDTO film : filmDTOList) {
            if (film.getGenres() == null || film.getGenres().isEmpty()) {
                continue;
            }
            for (String genre : film.getGenres()) {
                if (genre == null || genre.isEmpty()) {
                    continue;
                }
                EntityTransaction transaction = em.getTransaction();
                try {
                    transaction.begin();
                    serviceGenre.getOrCreateFromFilmDTO(genre);
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    LOGGER.warn("Couldn't create genre '{}' for film {}: {}", genre, film.getImdbId(), e.getMessage());
                }
            }
        }
    }
}