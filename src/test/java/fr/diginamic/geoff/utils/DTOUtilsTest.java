package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.PersonneDTO;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DTOUtilsTest {

    List<FilmDTO> films = new ArrayList<>();
    FilmDTO a = new FilmDTO();
    FilmDTO b = new FilmDTO();

    List<PersonneDTO> personnesDTOList = new ArrayList<>();
    List<ActeurDTO> acteurDTOList = new ArrayList<>();
    PersonneDTO personneDTO = new PersonneDTO();
    PersonneDTO personneDTO2 = new PersonneDTO();
    ActeurDTO acteurDTO = new ActeurDTO();
    ActeurDTO acteurDTO2 = new ActeurDTO();

    @Test
    public void mustFilter_Out_DuplicateTitle(){
        a.setNom("42");
        a.setAnneeSortie(Year.of(1984));
        b.setNom("42");
        b.setAnneeSortie(Year.of(1977));
        films.add(a);
        films.add(b);

        List<FilmDTO> listWODuplicates = DTOUtils.removeDuplicatesByNaturalId(films);

        assertEquals(1, listWODuplicates.size());
        assertNotEquals(films.getFirst().getAnneeSortie(), Year.of(1977));
    }

    @Test
    public void mustFiler_Out_Duplicate_Personnes(){
        personneDTO.setImdbId("sm29383");
        personneDTO2.setImdbId("sm29383");
        acteurDTO.setImdbId("wf929282");
        acteurDTO2.setImdbId("wf929282");

        personnesDTOList.add(personneDTO);
        personnesDTOList.add(personneDTO2);

        acteurDTOList.add(acteurDTO);
        acteurDTOList.add(acteurDTO2);

        acteurDTOList = DTOUtils.removeDuplicatesByNaturalId(acteurDTOList);
        personnesDTOList = DTOUtils.removeDuplicatesByNaturalId(personnesDTOList);

        assertEquals(1, acteurDTOList.size());
        assertEquals(1, personnesDTOList.size());
    }

}