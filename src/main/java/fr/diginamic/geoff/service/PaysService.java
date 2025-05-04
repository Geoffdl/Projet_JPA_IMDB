package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.PaysDTO;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.PaysMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class PaysService implements EntityService<Pays, PaysDTO> {
    EntityMapper<PaysDTO, Pays> roleMapper = new PaysMapper();

    @Override
    public List<Pays> createEntityList(List<FilmDTO> filmDTOList) {
        List<PaysDTO> paysDTOList = getList(filmDTOList);

        paysDTOList = DTOUtils.removeDuplicatesByNaturalId(paysDTOList); //remove duplicates

        List<Pays> paysList = paysDTOList.stream().map(p -> roleMapper.mapToEntity(p)).toList(); // map to simple entity

        return paysList;
    }

    //TODO Null handling
    @Override
    public List<PaysDTO> getList(List<FilmDTO> filmDTOList) {
        return filmDTOList.stream().map(p -> p.getPays()).toList();

    }


}