package fr.diginamic.geoff;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.parser.JsonParser;
import fr.diginamic.geoff.entity.Film;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.FilmMapper;
import fr.diginamic.geoff.utils.FilmDTOUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static final String JSONURL = "data/films.json";

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();


        try {
            JsonParser parser = new JsonParser();
            List<FilmDTO> films = parser.tryReading(FilmDTO.class, JSONURL);

            EntityMapper<FilmDTO, Film> filmMapper = new FilmMapper();

            List<FilmDTO> filmsWoDuplicates = FilmDTOUtils.removeDuplicateByTitle(films);


            AtomicInteger line = new AtomicInteger();
            filmsWoDuplicates.forEach(dto -> {
                EntityManager em = emf.createEntityManager();
                EntityTransaction transaction = em.getTransaction();
                try {
                    transaction.begin();
                    em.persist(filmMapper.mapToEntity(dto));
                    transaction.commit();
                } catch (Exception e) {
                    LOGGER.warn("Error at line {}", line.get());
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                } finally {
                    em.close();
                }
                line.getAndIncrement();
            });
            emf.close();


        } catch (IOException e) {

        }


    }
}