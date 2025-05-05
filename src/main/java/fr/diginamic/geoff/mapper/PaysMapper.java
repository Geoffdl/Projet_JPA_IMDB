package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.dto.PaysDTO;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.utils.DTOUtils;

public class PaysMapper implements EntityMapper<PaysDTO, Pays> {
    @Override
    public Pays mapToEntity(PaysDTO dto) {

        Pays pays = new Pays();

            pays.setNom(dto.getNom());
            pays.setUrl(dto.getUrl());


        return pays;
    }

    public PaysDTO NaissanceToDTO(NaissanceDTO dto){
        PaysDTO paysDTO = new PaysDTO();
        String paysNaissance = DTOUtils.extractPaysFromLieuNaissance(dto.getLieuNaissance());

        paysDTO.setNom(paysNaissance);

        return paysDTO;
    }


}