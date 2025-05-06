package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.exception.DuplicatePaysException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;


public class PaysDAO implements EntityDao<Pays> {

    private final EntityManager entityManager;

    public PaysDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Pays> findById(long id) {
        return Optional.ofNullable(entityManager.find(Pays.class, id));
    }

    @Override
    public Optional<Pays> findOrCreate(Pays pays) throws DuplicatePaysException {
        if (pays == null) {
            return Optional.empty();
        }
        Optional<Pays> existingPays = findByNom(pays.getNom());

        if (existingPays.isPresent()) {
            throw new DuplicatePaysException("Pays already exists: " + pays.getNom());

        }

        entityManager.persist(pays);
        return Optional.of(pays);
    }

    @Override
    public List<Pays> findAll() {
        TypedQuery<Pays> query = entityManager.createQuery("SELECT p FROM Pays p", Pays.class);
        return query.getResultList();
    }

    @Override
    public void delete(Pays pays) {
        if (pays != null) {
            entityManager.remove(pays);
        }
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
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}