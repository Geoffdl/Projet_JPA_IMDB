package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dao.PaysDAO;
import fr.diginamic.geoff.dto.LieuTournageDTO;
import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.dto.PaysDTO;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.mapper.PaysMapper;
import fr.diginamic.geoff.utils.PaysUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *
 */
public class ServicePays {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicePays.class);


    private final PaysMapper paysMapper = new PaysMapper();
    private final PaysDAO paysDAO;

    public ServicePays(PaysDAO paysDAO) {
        this.paysDAO = paysDAO;
    }

    /**
     * Get or create a Pays entity from a FilmDTO
     *
     * @param paysDTO data source
     * @return Pays instance
     */
    public Pays getOrCreateFromPaysDTO(PaysDTO paysDTO)  {
        if (paysDTO == null) {
            return null;
        }

        String cleanedName = PaysUtils.cleanCountryName(paysDTO.getNom());
        paysDTO.setNom(cleanedName);

        Pays pays = paysMapper.mapToEntity(paysDTO);

        Optional<Pays> result = paysDAO.findOrCreate(pays);
        return result.orElse(null);
    }

    /**
     * Get or create a Pays entity from a LieuTournageDTO
     *
     * @param dto data source
     * @return Pays instance
     */
    public Pays getOrCreateFromLieuTournage(LieuTournageDTO dto)  {
        if (dto == null) {
            return null;
        }

        PaysDTO paysDTO = paysMapper.TournageToDTO(dto);
        if (paysDTO == null || paysDTO.getNom() == null) {
            return null;
        }

        String cleanedName = PaysUtils.cleanCountryName(paysDTO.getNom());
        paysDTO.setNom(cleanedName);

        Pays pays = paysMapper.mapToEntity(paysDTO);

        Optional<Pays> result = paysDAO.findOrCreate(pays);
        return result.orElse(null);
    }

    /**
     * Get or create a Pays entity from a NaissanceDTO
     *
     * @param dto data source
     * @return Pays instance
     */
    public Pays getOrCreateFromNaissance(NaissanceDTO dto)  {
        if (dto == null) {
            return null;
        }

        PaysDTO paysDTO = paysMapper.NaissanceToDTO(dto);
        if (paysDTO == null || paysDTO.getNom() == null) {
            return null;
        }

        String cleanedName = PaysUtils.cleanCountryName(paysDTO.getNom());
        paysDTO.setNom(cleanedName);

        Pays pays = paysMapper.mapToEntity(paysDTO);

        Optional<Pays> result = paysDAO.findOrCreate(pays);
        return result.orElse(null);
    }


}