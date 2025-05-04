package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Film;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.FilmMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class FilmService {

    EntityMapper<FilmDTO, Film> filmMapper = new FilmMapper();

    /**
     * Take a list of FilmDTOs and returns a List of simple films without duplicates
     *
     * @param filmDTOS list of raw DTO
     * @return listOf films
     */
    public List<Film> createEntities(List<FilmDTO> filmDTOS) {


        filmDTOS = DTOUtils.removeDuplicatesByNaturalId(filmDTOS); //remove duplicates

        List<Film> filmList = filmDTOS.stream().map(p -> filmMapper.mapToEntity(p)).toList(); // map to simple entity

        return filmList;
    }


}