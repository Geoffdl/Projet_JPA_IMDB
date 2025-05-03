package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.entity.Genre;

public class GenreMapper implements EntityMapper<String, Genre> {
    @Override
    public Genre mapToEntity(String genreName) {

        Genre genre = new Genre();
        genre.setNom(genreName);

        return genre;
    }
}