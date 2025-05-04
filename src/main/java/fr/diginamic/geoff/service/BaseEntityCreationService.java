package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.*;
import fr.diginamic.geoff.parser.JsonParser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.util.List;

import static fr.diginamic.geoff.App.JSONURL;

public class BaseEntityCreationService {
    JsonParser parser = new JsonParser();

    ActeurService acteurService = new ActeurService();
    FilmService filmService = new FilmService();
    GenreService genreService = new GenreService();
    LangueService langueService = new LangueService();
    LieuService lieuService = new LieuService();
    PaysService paysService = new PaysService();
    RealisateurService realisateurService = new RealisateurService();
    RoleService roleService = new RoleService();

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");

    public void generateBaseEntityObjects() {

        List<FilmDTO> baseDtoList = null;
        try {
            baseDtoList = parser.tryReading(FilmDTO.class, JSONURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            persistActeurs(baseDtoList);
            persistFilms(baseDtoList);
            persistGenres(baseDtoList);
            persistLangues(baseDtoList);
            persistLieux(baseDtoList);
            persistRealisateurs(baseDtoList);
//            persistRoles(baseDtoList);
//            persistPays(baseDtoList);
        } finally {
            close();
        }


    }

    /**
     * closes the EMF when no longer needed
     */
    public void close() {
        emf.close();
    }

    /**
     * Generic method used to persist initial data inside the tables
     * Produces batch insert per entity type
     * Uses a single Entity Manager per process and closes at the end
     * @param <T>  the class of the entity to be persisted
     * @param entities list of said entities
     */
    //TODO DAO Implementation for UPSERT (update/insert) querying instead of raw inserts
    //TODO Proper error handling
    private <T> void persistEntityList(List<T> entities) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try (em) {
            transaction.begin();
            entities.forEach(em::persist);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Uses Service classes to generate specific entity list and persists them in the DB
     * @param baseDtoList dto data source
     */
    private void persistRealisateurs(List<FilmDTO> baseDtoList) {
        List<Realisateur> realisateurList = realisateurService.createEntityList(baseDtoList);
        persistEntityList(realisateurList);
    }
    /**
     * Uses Service classes to generate specific entity list and persists them in the DB
     * @param baseDtoList dto data source
     */
    private void persistActeurs(List<FilmDTO> baseDtoList) {

        List<Acteur> acteurList = acteurService.createEntityList(baseDtoList);
        persistEntityList(acteurList);

    }
    /**
     * Uses Service classes to generate specific entity list and persists them in the DB
     * @param baseDtoList dto data source
     */
    private void persistFilms(List<FilmDTO> baseDtoList) {
        List<Film> filmList = filmService.createEntityList(baseDtoList);
        persistEntityList(filmList);
    }
    /**
     * Uses Service classes to generate specific entity list and persists them in the DB
     * @param baseDtoList dto data source
     */
    private void persistGenres(List<FilmDTO> baseDtoList) {
        List<Genre> genreList = genreService.createEntityList(baseDtoList);
        persistEntityList(genreList);
    }
    /**
     * Uses Service classes to generate specific entity list and persists them in the DB
     * @param baseDtoList dto data source
     */
    private void persistLangues(List<FilmDTO> baseDtoList) {
        List<Langue> langueList = langueService.createEntityList(baseDtoList);
        persistEntityList(langueList);
    }
    /**
     * Uses Service classes to generate specific entity list and persists them in the DB
     * @param baseDtoList dto data source
     */
    private void persistLieux(List<FilmDTO> baseDtoList) {
        List<Lieu> lieuList = lieuService.createEntityList(baseDtoList);
        persistEntityList(lieuList);
    }
    /**
     * Uses Service classes to generate specific entity list and persists them in the DB
     * @param baseDtoList dto data source
     */
    private void persistPays(List<FilmDTO> baseDtoList) {
        List<Pays> paysList = paysService.createEntityList(baseDtoList);
        persistEntityList(paysList);
    }
    /**
     * Uses Service classes to generate specific entity list and persists them in the DB
     * @param baseDtoList dto data source
     */
    private void persistRoles(List<FilmDTO> baseDtoList) {
        List<Role> roleList = roleService.createEntityList(baseDtoList);
        persistEntityList(roleList);
    }


}