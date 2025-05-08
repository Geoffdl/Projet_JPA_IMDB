package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class FilmDAO
{
    private final EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(FilmDAO.class);

    public FilmDAO(EntityManager em) {
        this.em = em;
    }



    public Optional<Film> findByImdbId(String imdbId) {
        try{
            TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.imdbId = :imdbId", Film.class) ;
            query.setParameter("imdbId", imdbId);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    public Film create(Film newFilm) {
        em.persist(newFilm);
        return newFilm;
    }

    public void runInTransaction(Runnable action, EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        try {
            if (!transaction.isActive()) transaction.begin();
            action.run();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            LOGGER.error("Transaction Error : {}", e.getMessage());

        }
    }
}