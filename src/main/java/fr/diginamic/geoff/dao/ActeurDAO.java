package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Acteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class ActeurDAO {

    private final EntityManager em;

    public ActeurDAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Acteur> findByIdentite(String identite) {
        try {
            TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :identite",
                    Acteur.class);
            query.setParameter("identite", identite);

            return Optional.of(query.getSingleResult());

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<Acteur> findByImdbId(String imdbId) {
        try {
            TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.imdbId = :imdbId", Acteur.class);
            query.setParameter("imdbId", imdbId);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Acteur create(Acteur newActeur) {
        em.persist(newActeur);
        return newActeur;
    }
}