package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.entity.Lieu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class LieuDAO
{
    private final EntityManager em;

    public LieuDAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Lieu> findByVilleAndRegion(String ville, String region) {
        try {
            TypedQuery<Lieu> query = em.createQuery(
                    "SELECT l FROM Lieu l WHERE l.ville = :ville AND l.region = :region",
                    Lieu.class);
            query.setParameter("ville", ville);
            query.setParameter("region", region);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Lieu create(Lieu lieu){
        em.persist(lieu);
        return lieu;
    }
}