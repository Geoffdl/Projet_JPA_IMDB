package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Pays;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;


public class PaysDAO {

    private final EntityManager entityManager;

    public PaysDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Pays> findOrCreate(Pays pays) {
        if (pays == null) {
            return Optional.empty();
        }
        Optional<Pays> existingPays = findByNom(pays.getNom());

        if (existingPays.isPresent()) {
            return existingPays;

        }

        entityManager.persist(pays);
        return Optional.of(pays);
    }

    /**
     * Find a Pays entity by its name (natural key)
     *
     * @param nom The country name to search for
     * @return Optional containing the Pays if found, empty otherwise
     */
    public Optional<Pays> findByNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            return Optional.empty();
        }
        try {
            TypedQuery<Pays> query = entityManager.createQuery(
                    "SELECT p FROM Pays p WHERE p.nom = :nom", Pays.class);
            query.setParameter("nom", nom);

            query.setHint("org.hibernate.cacheable", "true");

            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}