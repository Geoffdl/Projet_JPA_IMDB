package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.LieuTournageDTO;
import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.entity.Lieu;
import fr.diginamic.geoff.mapper.LieuNaissanceMapper;
import fr.diginamic.geoff.mapper.LieuTournageMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;
import java.util.stream.Stream;

public class LieuService implements EntityService<Lieu, LieuTournageDTO> {

    LieuNaissanceMapper lieuNaissanceMapper = new LieuNaissanceMapper();
    LieuTournageMapper lieuTournageMapper = new LieuTournageMapper();

    @Override
    public List<Lieu> createEntityList(List<FilmDTO> filmDTOList) {

        List<Lieu> lieuTournage = createEntityFromTournage(filmDTOList);
        List<Lieu> lieuNaissance = createEntityFromNaissance(filmDTOList);

        List<Lieu> concatenedList = Stream.concat(lieuTournage.stream(), lieuNaissance.stream()).distinct().toList();

        return concatenedList;
    }

    @Override
    public List<LieuTournageDTO> getList(List<FilmDTO> filmDTOList) {

        List<LieuTournageDTO> lieuTournageDTOList = filmDTOList.stream().map(f -> f.getLieuTournage()).toList();

        lieuTournageDTOList = DTOUtils.removeDuplicatesByNaturalId(lieuTournageDTOList);

        return lieuTournageDTOList;
    }

    /**
     * @param filmDTOList
     * @return
     */
    private List<Lieu> createEntityFromNaissance(List<FilmDTO> filmDTOList) {

        List<NaissanceDTO> lieuNaissance = getListLieuNaissance(filmDTOList);

        return lieuNaissance.stream().map(l -> lieuNaissanceMapper.mapToEntity(l)).distinct().toList();
    }

    private List<Lieu> createEntityFromTournage(List<FilmDTO> filmDTOList) {
        List<LieuTournageDTO> lieuTournageDTOS = getList(filmDTOList);

        return lieuTournageDTOS.stream().map(l -> lieuTournageMapper.mapToEntity(l)).distinct().toList();
    }

    /**
     *
     * @param filmDTOList
     * @return
     */
    private List<NaissanceDTO> getListLieuNaissance(List<FilmDTO> filmDTOList) {
        List<NaissanceDTO> lieuNaissanceActeurs = filmDTOList.stream().flatMap(f -> f.getCastingPrincipal()
                .stream().map(casting -> casting.getNaissance())).toList();

        List<NaissanceDTO> lieuNaissanceRealisateurs = filmDTOList.stream().flatMap(f -> f.getRealisateurs()
                .stream().map(real -> real.getNaissance())).toList();

        List<NaissanceDTO> concatenedList = Stream.concat(lieuNaissanceActeurs.stream(), lieuNaissanceRealisateurs.stream()).distinct().toList();

        return concatenedList;
    }
}