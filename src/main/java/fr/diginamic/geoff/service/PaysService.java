package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.PaysDTO;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.PaysMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class PaysService {
    EntityMapper<PaysDTO, Pays> roleMapper = new PaysMapper();

    /**
     * Take a list of roleDTOS and returns a List of simple Pays without duplicates
     *
     * @param filmDTOS list of raw DTO
     * @return listOf Pays
     */
    public List<Pays> createEntities(List<FilmDTO> filmDTOS) {

        List<PaysDTO> paysDTOList = getList(filmDTOS);

        paysDTOList = DTOUtils.removeDuplicatesByNaturalId(paysDTOList); //remove duplicates

        List<Pays> paysList = paysDTOList.stream().map(p -> roleMapper.mapToEntity(p)).toList(); // map to simple entity

        return paysList;
    }

    /**
     * Generates a list of all roles across all films;
     *
     * @param filmDTOS raw list of film dtos
     * @return list of roles
     */
    private List<PaysDTO> getList(List<FilmDTO> filmDTOS) {
        return filmDTOS.stream().map(p -> p.getPays()).toList();
    }
}