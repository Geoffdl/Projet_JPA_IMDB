package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.FilmDTO;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FilmUtilsTest {

    List<FilmDTO> films = new ArrayList<>();
    FilmDTO a = new FilmDTO();
    FilmDTO b = new FilmDTO();

    @Test
    public void mustFilter_Out_DuplicateTitle(){
        a.setNom("42");
        a.setAnneeSortie(Year.of(1984));
        b.setNom("42");
        b.setAnneeSortie(Year.of(1977));
        films.add(a);
        films.add(b);

        List<FilmDTO> listWODuplicates = FilmUtils.removeDuplicateByTitle(films);

        assertEquals(1, listWODuplicates.size());
        assertNotEquals(films.getFirst().getAnneeSortie(), Year.of(1977));
    }

}