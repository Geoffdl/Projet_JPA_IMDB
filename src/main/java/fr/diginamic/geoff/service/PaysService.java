package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.*;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.mapper.PaysMapper;
import fr.diginamic.geoff.utils.DTOUtils;
import fr.diginamic.geoff.utils.PaysUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The purpose of this service class is to execute the conversion from DTO data to Pays entity
 * it handles 3 different data inputs :
 * - birthplaces (has to find the country inside a string)
 * - film shooting places (has to select the country element of a nested object)
 * - film country component 1-1 addition
 */
public class PaysService implements EntityService<Pays, PaysDTO> {
    PaysMapper paysMapper = new PaysMapper();


    @Override
    public List<Pays> createEntityList(List<FilmDTO> filmDTOList) {

        List<Pays> paysList1 = createEntityFromPaysDTO(filmDTOList);
        List<Pays> paysList2 = createEntityFromNaissanceDTO(filmDTOList);
        List<Pays> paysList3 = createEntityFromLieuTournageDTO(filmDTOList);

        List<Pays> list1And2 = Stream.concat(paysList1.stream(), paysList2.stream()).distinct().filter(Objects::nonNull).toList();
        return Stream.concat(list1And2.stream(), paysList3.stream()).distinct().filter(Objects::nonNull).toList();
    }

    @Override
    public List<PaysDTO> getList(List<FilmDTO> filmDTOList) {

        return filmDTOList.stream()
                .map(FilmDTO::getPays)
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * Collects list of Naissance from data source
     *
     * @param filmDTOList data source
     * @return list of naissanceDTOs before conversion
     */
    private List<NaissanceDTO> getListFromNaissance(List<FilmDTO> filmDTOList) {
        ActeurService acteurService = new ActeurService();

        List<ActeurDTO> acteurDTOList = acteurService.getList(filmDTOList);

        return acteurDTOList.stream().map(acteurDTO -> acteurDTO.getNaissance()).toList();

    }

    /**
     * Collects list of LieuTournage from data source
     *
     * @param filmDTOList data source
     * @return list of LieuTournageDTO before conversion
     */
    private List<LieuTournageDTO> getListFromLieuTournage(List<FilmDTO> filmDTOList) {

        return filmDTOList.stream().map(f -> f.getLieuTournage()).toList();

    }

    /**
     * Finds and converts Pays from Lieu
     * Additional : pre filter duplicates and format data
     *
     * @param filmDTOList data source
     * @return pays entityList
     */
    private List<Pays> createEntityFromLieuTournageDTO(List<FilmDTO> filmDTOList) {
        List<LieuTournageDTO> lieuTournageDTOS = getListFromLieuTournage(filmDTOList);

        List<PaysDTO> paysDTOS = lieuTournageDTOS.stream().map(l -> paysMapper.TournageToDTO(l)).toList();

        paysDTOS = DTOUtils.removeDuplicatesByNaturalId(paysDTOS); //remove duplicates

        return paysDTOS.stream().map(p -> paysMapper.mapToEntity(p)).toList();// map to simple entity
    }

    /**
     * Finds and converts Pays from PaysDTO
     * Additional : pre filter duplicates and format data
     *
     * @param filmDTOList data source
     * @return pays entityList
     */
    private List<Pays> createEntityFromPaysDTO(List<FilmDTO> filmDTOList) {

        List<PaysDTO> paysDTOList = getList(filmDTOList);

        paysDTOList.forEach(p -> { // clean up names
            String cleanedPays = PaysUtils.cleanCountryName(p.getNom());
            p.setNom(cleanedPays);
        });
        paysDTOList = DTOUtils.removeDuplicatesByNaturalId(paysDTOList); //remove duplicates

        return paysDTOList.stream().map(p -> paysMapper.mapToEntity(p)).toList();     // map to simple entity
    }

    /**
     * Finds and converts Pays from Naissance
     * Additional : pre filter duplicates and format data
     *
     * @param filmDTOList data source
     * @return pays entityList
     */
    private List<Pays> createEntityFromNaissanceDTO(List<FilmDTO> filmDTOList) {
        List<NaissanceDTO> naissanceDTOList = getListFromNaissance(filmDTOList);

        List<PaysDTO> intermediateList = naissanceDTOList.stream().map(p -> paysMapper.NaissanceToDTO(p)).toList();

        intermediateList = DTOUtils.removeDuplicatesByNaturalId(intermediateList); //remove duplicates

        intermediateList.forEach(p -> { //clean up names
            String cleanedPays = PaysUtils.cleanCountryName(p.getNom());
            p.setNom(cleanedPays);
        });

        return intermediateList.stream().map(p -> paysMapper.mapToEntity(p)).toList(); // map to simple entity
    }

}