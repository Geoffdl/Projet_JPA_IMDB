package fr.diginamic.geoff;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.parser.JsonParser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class App
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static final String JSONURL = "src/main/resources/films.json";

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


        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }
}