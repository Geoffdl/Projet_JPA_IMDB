package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.dto.PaysDTO;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.mapper.PaysMapper;
import fr.diginamic.geoff.utils.DTOUtils;
import fr.diginamic.geoff.utils.PaysUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public class PaysService implements EntityService<Pays, PaysDTO> {
    PaysMapper paysMapper = new PaysMapper();


    @Override
    public List<Pays> createEntityList(List<FilmDTO> filmDTOList) {

        List<Pays> paysList1 = createEntityFromPaysDTO(filmDTOList);
        List<Pays> paysList2 = createEntityFromNaissanceDTO(filmDTOList);

        return Stream.concat(paysList1.stream(), paysList2.stream()).distinct().toList();
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
     * @param filmDTOList
     * @return
     */
    private List<NaissanceDTO> getListFromNaissance(List<FilmDTO> filmDTOList) {
        ActeurService acteurService = new ActeurService();

        List<ActeurDTO> acteurDTOList = acteurService.getList(filmDTOList);

        return acteurDTOList.stream().map(acteurDTO -> acteurDTO.getNaissance()).toList();

    }

    /**
     * @param filmDTOList
     * @return
     */
    private List<Pays> createEntityFromPaysDTO(List<FilmDTO> filmDTOList) {

        List<PaysDTO> paysDTOList = getList(filmDTOList);
        paysDTOList = DTOUtils.removeDuplicatesByNaturalId(paysDTOList); //remove duplicates

        paysDTOList.forEach(p -> {
            String cleanedPays = PaysUtils.cleanCountryName(p.getNom());
            p.setNom(cleanedPays);
        });

        return paysDTOList.stream().map(p -> paysMapper.mapToEntity(p)).toList();     // map to simple entity
    }

    /**
     * @param filmDTOList
     * @return
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