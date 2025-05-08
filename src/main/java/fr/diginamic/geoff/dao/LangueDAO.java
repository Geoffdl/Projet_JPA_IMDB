package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Langue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class LangueDAO implements Dao<Langue> {

   private final EntityManager em;

    public LangueDAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Langue> findByNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            return Optional.empty();
        }

        try {
            TypedQuery<Langue> query = em.createQuery(
                    "SELECT l FROM Langue l WHERE l.nom = :nom", Langue.class);
            query.setParameter("nom", nom);

            query.setHint("org.hibernate.cacheable", "true");

            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Langue create(Langue langue){
        em.persist(langue);
        return langue;
    }



}