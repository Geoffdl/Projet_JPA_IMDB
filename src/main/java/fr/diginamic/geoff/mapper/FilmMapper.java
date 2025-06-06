package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Year;
import java.util.ArrayList;

public class FilmMapper implements EntityMapper<FilmDTO, Film> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilmMapper.class);

    @Override
    public Film mapToEntity(FilmDTO dto) {
        Film film = new Film();

        film.setImdbId(mapAttributeImdbId(dto));
        film.setTitre(mapAttributeTitre(dto));
        film.setResume(mapAttributeResume(dto));
        film.setAnnee(mapAttributeAnnee(dto));
        film.setRating(mapAttributeRating(dto));
        film.setRoles(new ArrayList<>());

        return film;
    }

    /**
     *
     * @param dto
     * @return
     */
    private String mapAttributeImdbId(FilmDTO dto)  {
        if(dto.getImdbId() == null){
            LOGGER.warn("Missing Imdb Id for film: {}", "x");
            return null;
        }
        return dto.getImdbId();
    }

    /**
     *
     * @param dto
     * @return
     */
    private String mapAttributeTitre(FilmDTO dto) {
        if(dto.getNom() == null){
            LOGGER.warn("Missing title for film: {}", "x");
            return null;
        }
        return dto.getNom();
    }
    private Year mapAttributeAnnee(FilmDTO dto) {
        return dto.getAnneeSortie();
    }
    private Float mapAttributeRating(FilmDTO dto){
        return dto.getRating();
    }
    private String mapAttributeResume(FilmDTO dto){
        return dto.getPlot();
    }

}