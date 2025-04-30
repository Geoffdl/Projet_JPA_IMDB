package fr.diginamic.geoff;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello, World!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

    }
}