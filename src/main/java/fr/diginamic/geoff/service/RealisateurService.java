package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.PersonneDTO;
import fr.diginamic.geoff.entity.Realisateur;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.PersonneMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class RealisateurService {

    EntityMapper<PersonneDTO, Realisateur> personneMapper = new PersonneMapper();

    /**
     * Take a list of FilmDTOs and returns a List of simple Realisateurs without duplicates
     *
     * @param filmDTOS list of raw DTO
     * @return listOf Realisateurs
     */
    public List<Realisateur> createEntities(List<FilmDTO> filmDTOS) {

        List<PersonneDTO> personneDTOList = getList(filmDTOS);

        personneDTOList = DTOUtils.removeDuplicatesByNaturalId(personneDTOList); //remove duplicates

        List<Realisateur> realisateurList = personneDTOList.stream().map(p -> personneMapper.mapToEntity(p)).toList(); // map to simple entity

        return realisateurList;
    }

    /**
     * Generates a list of all realisateurs across all films;
     *
     * @param filmDTOS raw list of film dtos
     * @return list of realisateurs
     */
    private List<PersonneDTO> getList(List<FilmDTO> filmDTOS) {
        return filmDTOS.stream().flatMap(d -> d.getRealisateurs().stream()).toList();
    }
}