package fr.diginamic.geoff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.parser.JsonParser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.File;
import java.io.IOException;

import java.util.List;


public class Main
{
    private static final String JSONURL = "D:\\Geoff\\Diginamic\\CDA\\Cours\\18_Projet_JPA_Individuel\\Projet\\Projet_IMDB\\src\\main\\resources\\films.json";

    public static void main(String[] args)
    {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        em.close();
        emf.close();
        try
        {
            JsonParser parser = new JsonParser();
            List<FilmDTO> films = parser.tryReading(FilmDTO.class, JSONURL);

            films.stream().forEach(filmDTO -> System.out.println(filmDTO));


        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }
}