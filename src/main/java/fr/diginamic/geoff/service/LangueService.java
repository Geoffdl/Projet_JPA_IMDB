package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Langue;

import java.util.List;

public class LangueService implements EntityService<Langue, String> {

    //TODO IMPLEMENTATION
    @Override
    public List<Langue> createEntityList(List<FilmDTO> filmDTOList) {
        return List.of();
    }

    //TODO IMPLEMENTATION
    @Override
    public List<String> getList(List<FilmDTO> filmDTOList) {
        return List.of();
    }
}