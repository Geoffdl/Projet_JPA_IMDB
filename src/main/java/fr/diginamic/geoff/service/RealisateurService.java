package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.PersonneDTO;
import fr.diginamic.geoff.entity.Realisateur;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.PersonneMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class RealisateurService implements EntityService<Realisateur, PersonneDTO> {

    EntityMapper<PersonneDTO, Realisateur> personneMapper = new PersonneMapper();

    @Override
    public List<Realisateur> createEntityList(List<FilmDTO> filmDTOList) {
                List<PersonneDTO> personneDTOList = getList(filmDTOList);

        personneDTOList = DTOUtils.removeDuplicatesByNaturalId(personneDTOList); //remove duplicates

        List<Realisateur> realisateurList = personneDTOList.stream().map(p -> personneMapper.mapToEntity(p)).toList(); // map to simple entity

        return realisateurList;
    }

    @Override
    public List<PersonneDTO> getList(List<FilmDTO> filmDTOList) {
        return filmDTOList.stream().flatMap(d -> d.getRealisateurs().stream()).toList();
    }
    
}