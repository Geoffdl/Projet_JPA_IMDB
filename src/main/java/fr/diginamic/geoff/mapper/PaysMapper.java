package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.PaysDTO;
import fr.diginamic.geoff.entity.Pays;

public class PaysMapper implements EntityMapper<PaysDTO, Pays> {
    @Override
    public Pays mapToEntity(PaysDTO dto) {

        Pays pays = new Pays();

        pays.setNom(dto.getNom());
        pays.setUrl(dto.getUrl());

        return pays;
    }
}