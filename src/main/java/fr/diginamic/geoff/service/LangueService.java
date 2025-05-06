package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Langue;
import fr.diginamic.geoff.mapper.LangueMapper;

import java.util.List;

public class LangueService implements EntityService<Langue, String> {
    LangueMapper langueMapper = new LangueMapper();
    @Override
    public List<Langue> createEntityList(List<FilmDTO> filmDTOList) {

        List<String> langueList = getList(filmDTOList);
        return langueList.stream().map(l-> langueMapper.mapToEntity(l)).distinct().toList();
    }

    @Override
    public List<String> getList(List<FilmDTO> filmDTOList) {
        return filmDTOList.stream().map(f-> f.getLangue()).toList();
    }

    public List<Langue> getLanguesForFilmDTO(FilmDTO dto) {
        if (dto.getLangue() == null) {
            return List.of();
        }
        return List.of(langueMapper.mapToEntity(dto.getLangue()));
    }

}