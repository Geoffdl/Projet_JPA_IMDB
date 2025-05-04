package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.mapper.ActeurMapper;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class ActeurService {
    EntityMapper<ActeurDTO, Acteur> acteurMapper = new ActeurMapper();

    /**
     * Take a list of FilmDTOs and returns a List of simple Realisateurs without duplicates
     *
     * @param filmDTOS list of raw DTO
     * @return listOf Realisateurs
     */
    public List<Acteur> createEntities(List<FilmDTO> filmDTOS) {

        List<ActeurDTO> acteurDTOList = getList(filmDTOS);

        acteurDTOList = DTOUtils.removeDuplicatesByNaturalId(acteurDTOList); //remove duplicates

        List<Acteur> acteursList = acteurDTOList.stream().map(p -> acteurMapper.mapToEntity(p)).toList(); // map to simple entity

        return acteursList;
    }

    /**
     * Generates a list of all realisateurs for accross all films;
     *
     * @param filmDTOS raw list of film dtos
     * @return list of realisateurs
     */
    private List<ActeurDTO> getList(List<FilmDTO> filmDTOS) {
        return filmDTOS.stream().flatMap(d -> d.getCastingPrincipal().stream()).toList();
    }
}