package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Genre;
import fr.diginamic.geoff.mapper.GenreMapper;

import java.util.List;
import java.util.Objects;

public class GenreService implements EntityService<Genre, String> {

    GenreMapper genreMapper = new GenreMapper();

    @Override
    public List<Genre> createEntityList(List<FilmDTO> filmDTOList) {

        List<String> genreList = getList(filmDTOList);

        return genreList.stream().map(g-> genreMapper.mapToEntity(g)).distinct().toList();
    }

    @Override
    public List<String> getList(List<FilmDTO> filmDTOList) {

        List<String> genreList = filmDTOList.stream().flatMap(f-> f.getGenres().stream()).filter(Objects::nonNull).toList();

        return genreList;
    }
}