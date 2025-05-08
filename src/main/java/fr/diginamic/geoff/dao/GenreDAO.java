package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class GenreDAO
{
    private final EntityManager em;


    public GenreDAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Genre> findByNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            return Optional.empty();
        }

        try {
            TypedQuery<Genre> query = em.createQuery(
                    "SELECT g FROM Genre g WHERE g.nom = :nom", Genre.class);
            query.setParameter("nom", nom);

            query.setHint("org.hibernate.cacheable", "true");

            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Genre create(Genre genre){
        em.persist(genre);
        return genre;
    }
}