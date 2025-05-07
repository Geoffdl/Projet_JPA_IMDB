package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dao.GenreDAO;
import fr.diginamic.geoff.entity.Genre;
import fr.diginamic.geoff.mapper.GenreMapper;

import java.util.Optional;

public class ServiceGenre {
    private final GenreMapper genreMapper;
    private final GenreDAO genreDAO;

    public ServiceGenre(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
        this.genreMapper = new GenreMapper();
    }

    /**
     *
     * @param nom
     * @return
     */
    public Genre getOrCreateFromFilmDTO(String nom) {
        if (nom == null) {
            return null;
        }

        Optional<Genre> existingGenre = genreDAO.findByNom(nom);
        if (existingGenre.isPresent()) {
            return existingGenre.get();
        }

        Genre newGenre = genreMapper.mapToEntity(nom);
        return genreDAO.create(newGenre);
    }
}