package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Genre;

import java.util.List;

public class GenreService implements EntityService<Genre, String> {

    //TODO IMPLEMENTATION
    @Override
    public List<Genre> createEntityList(List<FilmDTO> filmDTOList) {
        return List.of();
    }

    //TODO IMPLEMENTATION
    @Override
    public List<String> getList(List<FilmDTO> filmDTOList) {
        return List.of();
    }
}