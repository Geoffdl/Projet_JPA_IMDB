package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.mapper.ActeurMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class ActeurService implements EntityService<Acteur, ActeurDTO> {

    private final ActeurMapper mapper = new ActeurMapper();
//    EntityMapper<ActeurDTO, Acteur> acteurMapper = new ActeurMapper();

    @Override
    public List<Acteur> createEntityList(List<FilmDTO> filmDTOList) {

        List<ActeurDTO> acteurDTOList = getList(filmDTOList);

        acteurDTOList = DTOUtils.removeDuplicatesByNaturalId(acteurDTOList); //remove duplicates

//        List<Acteur> acteursList = acteurDTOList.stream().map(p -> acteurMapper.mapToEntity(p)).toList(); // map to simple entity
        List<Acteur> acteursList = acteurDTOList.stream().map(p -> mapper.mapToEntity(p)).toList(); // map to simple entity

        return acteursList;
    }

    @Override
    public List<ActeurDTO> getList(List<FilmDTO> filmDTOList) {
        return filmDTOList.stream().flatMap(d -> d.getCastingPrincipal().stream()).toList();
    }

}