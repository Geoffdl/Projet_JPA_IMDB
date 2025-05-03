package fr.diginamic.geoff.Jpa;

import fr.diginamic.geoff.entity.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEntries {


    @Test
    public void findFilmsWithDuplicateTitles() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            List<String> duplicateTitles = em.createQuery(
                    "SELECT f.titre FROM Film f GROUP BY f.titre HAVING COUNT(f) > 1", String.class
            ).getResultList();

            if (duplicateTitles.isEmpty()) {
                System.out.println("No duplicate titles found.");
                return;
            }

            // Step 2: Fetch all films with those
            List<Film> filmsWithDuplicateTitles = em.createQuery(
                            "SELECT f FROM Film f WHERE f.titre IN :titles", Film.class
                    ).setParameter("titles", duplicateTitles)
                    .getResultList();

            filmsWithDuplicateTitles.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
            emf.close();
        }
    }
}