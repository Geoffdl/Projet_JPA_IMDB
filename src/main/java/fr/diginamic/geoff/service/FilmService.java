package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Film;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.FilmMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class FilmService implements EntityService<Film, FilmDTO> {

    EntityMapper<FilmDTO, Film> filmMapper = new FilmMapper();

    @Override
    public List<Film> createEntityList(List<FilmDTO> filmDTOList) {
        filmDTOList = DTOUtils.removeDuplicatesByNaturalId(filmDTOList); //remove duplicates

        List<Film> filmList = filmDTOList.stream().map(p -> filmMapper.mapToEntity(p)).toList(); // map to simple entity

        return filmList;
    }

    //not needed in this case
    @Override
    public List<FilmDTO> getList(List<FilmDTO> filmDTOList) {
        return List.of();
    }
}