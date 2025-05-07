package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Realisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class RealisateurDAO {
    private final EntityManager em;

    public RealisateurDAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Realisateur> findByIdentite(String identite) {
        try {
            TypedQuery<Realisateur> query = em.createQuery("SELECT r FROM Realisateur r WHERE r.identite = :identite",
                    Realisateur.class);
            query.setParameter("identite", identite);

            return Optional.of(query.getSingleResult());

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<Realisateur> findByImdbId(String imdbId) {
        try {
            TypedQuery<Realisateur> query = em.createQuery("SELECT r FROM Realisateur r WHERE r.imdbId = :imdbId",
                    Realisateur.class);
            query.setParameter("imdbId", imdbId);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Realisateur create(Realisateur newRealisateur) {
        em.persist(newRealisateur);
        return newRealisateur;
    }
}