package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class PersonneDAO
{
    private final EntityManager em;

    public PersonneDAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Personne> findByImdbId(String imdbId) {
        try {
            TypedQuery<Personne> query = em.createQuery(
                    "SELECT p FROM Personne p WHERE p.imdbId = :imdbId", Personne.class);
            query.setParameter("imdbId", imdbId);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}