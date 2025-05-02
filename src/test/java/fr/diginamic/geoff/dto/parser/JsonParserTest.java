package fr.diginamic.geoff.dto.parser;

import fr.diginamic.geoff.dto.FilmDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static fr.diginamic.geoff.App.JSONURL;
import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    /*
    It should read the json file and contain it inside a list of FilmDTO
     */
    @Test
    public void test_tryReading() throws IOException {
        JsonParser parser = new JsonParser();
        List<FilmDTO> films = parser.tryReading(FilmDTO.class, JSONURL);
        String id = films.getFirst().getRealisateurs().getFirst().getIdentite();
        //films.stream().forEach(System.out::println);
        int nbOfFilms = films.size();

        assertEquals(2748, nbOfFilms);
        assertEquals("John Irvin", id);
    }
}