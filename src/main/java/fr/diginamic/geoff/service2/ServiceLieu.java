package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dao.LieuDAO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.LieuTournageDTO;
import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.entity.Lieu;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.mapper.LieuNaissanceMapper;
import fr.diginamic.geoff.mapper.LieuTournageMapper;

import java.util.Optional;

import static fr.diginamic.geoff.utils.LieuUtils2.extractLieuRegion;
import static fr.diginamic.geoff.utils.LieuUtils2.extractLieuVille;

public class ServiceLieu {
    private final LieuNaissanceMapper lieuNaissanceMapper = new LieuNaissanceMapper();
    private final LieuTournageMapper lieuTournageMapper = new LieuTournageMapper();
    private final ServicePays servicePays;
    private LieuDAO lieuDAO;

    public ServiceLieu(ServicePays servicePays, LieuDAO lieuDAO) {
        this.servicePays = servicePays;
        this.lieuDAO = lieuDAO;
    }

    /**
     *
     * @param lieuTournageDTO
     * @return
     */
    public Lieu getOrCreateFromLieuTournage(LieuTournageDTO lieuTournageDTO) {
        if (lieuTournageDTO == null) {
            return null;
        }

        String ville = lieuTournageDTO.getVille().trim();
        String region = lieuTournageDTO.getEtatDept().trim();

        Optional<Lieu> existingLieu = lieuDAO.findByVilleAndRegion(ville, region);
        if (existingLieu.isPresent()) {
            return existingLieu.get();
        }

        Lieu newLieu = lieuTournageMapper.mapToEntity(lieuTournageDTO);

        if (lieuTournageDTO.getPays() != null) {
            Pays pays = servicePays.getOrCreateFromLieuTournage(lieuTournageDTO);
            newLieu.setPays(pays);
        }

        return lieuDAO.create(newLieu);
    }

    /**
     *
     * @param naissanceDTO
     * @return
     */
    public Lieu getOrCreateFromNaissance(NaissanceDTO naissanceDTO) {
        if (naissanceDTO == null) {
            return null;
        }

        String ville = extractLieuVille(naissanceDTO);
        String region = extractLieuRegion(naissanceDTO);

        Optional<Lieu> existingLieu = lieuDAO.findByVilleAndRegion(ville,region);
        if (existingLieu.isPresent()) {
            return existingLieu.get();
        }

        Lieu newLieu = lieuNaissanceMapper.mapToEntity(naissanceDTO);

        Pays pays = servicePays.getOrCreateFromNaissance(naissanceDTO);
        if(pays != null){
            newLieu.setPays(pays);
        }
        return lieuDAO.create(newLieu);
    }

    /**
     *
     * @param filmDTO
     * @return
     */
    public Lieu getOrCreateFromFilmDTO(FilmDTO filmDTO){
        if(filmDTO ==null){return null;}
        return getOrCreateFromLieuTournage(filmDTO.getLieuTournage());
    }
}