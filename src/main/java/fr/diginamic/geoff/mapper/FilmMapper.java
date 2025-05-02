package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.App;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.LieuTournageDTO;
import fr.diginamic.geoff.entity.*;
import fr.diginamic.geoff.exception.InvalidFilmDataException;
import fr.diginamic.geoff.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Year;
import java.util.List;

public class FilmMapper implements EntityMapper<FilmDTO, Film> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilmMapper.class);

    @Override
    public Film mapToEntity(FilmDTO dto) throws InvalidFilmDataException {
        Film film = new Film();

        film.setImdbId(mapAttributeImdbId(dto));
        film.setTitre(mapAttributeTitre(dto));
        film.setAnnee(mapAttributeAnnee(dto));
        film.setRating(mapAttributeRating(dto));

        return null;
    }

    private String mapAttributeImdbId(FilmDTO dto) throws InvalidFilmDataException {
        if(dto.getId() == null){
            LOGGER.warn("Missing Imdb Id for film: {}", dto.getId());
            throw new InvalidFilmDataException("imdbId is missing for film: " + dto.getId());
        }
        return dto.getId();
    }
    private String mapAttributeTitre(FilmDTO dto) {
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
    private Lieu mapAttributeLieuTournage(FilmDTO dto){
        /*
        LieuTournageDTO lieuDto = dto.getLieuTournage();

        String[] localisation = StringUtils.stringToArrayOfStrings(lieuDto.getVille(), "-");
        String ville = localisation[0];
        String libelle = localisation[1];

        Lieu lieu = new Lieu();
        lieu.setVille(ville);
        lieu.setRegion(lieuDto.getEtatDept());
        lieu.setLibelle(libelle);


        Pays pays = new Pays();
        pays.setNom(dto.getLieuTournage().getPays());

        lieu.setPays(pays);
         */
        return null;
    }
    private Pays mapAttributePays(FilmDTO dto){
        return null;
    }
    private List<Role> mapAttributeRoles(FilmDTO dto){
        return null;
    }
    private List<Langue> mapAttributeLangues(FilmDTO dto){
        return null;
    }
    private List<Genre> mapAttributeGenres(FilmDTO dto){
        return null;
    }
    private List<Realisateur> mapAttributeRealisateurs(FilmDTO dto){
        return null;
    }
    private List<Acteur> mapAttributeActeurs(FilmDTO dto){
        return null;
    }
}