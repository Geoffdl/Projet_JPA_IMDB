package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.entity.Film;
import fr.diginamic.geoff.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class RoleDAO {
    private final EntityManager em;

    public RoleDAO(EntityManager em) {
        this.em = em;
    }

    public Role create(Role role) {
        em.persist(role);
        return role;
    }

    public Optional<Role> findByPersonnage(String personnage) {
        try {
            TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.personnage = :personnage", Role.class);
            query.setParameter("personnage", personnage);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<Role> findByFilmAndActeurAndPersonnage(Film film, Acteur acteur, String personnage) {
        try {
            TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.personnage = :personnage AND r.film = :film AND r.acteur = :acteur", Role.class);
            query.setParameter("personnage", personnage);
            query.setParameter("film", film);
            query.setParameter("acteur", acteur);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}