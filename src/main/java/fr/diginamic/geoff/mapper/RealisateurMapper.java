package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.PersonneDTO;
import fr.diginamic.geoff.entity.Realisateur;

public class RealisateurMapper implements EntityMapper<PersonneDTO, Realisateur> {
    @Override
    public Realisateur mapToEntity(PersonneDTO dto) {

        Realisateur realisateur = new Realisateur();

        realisateur.setIdentite(dto.getIdentite());
        realisateur.setDateNaissance(dto.getNaissance().getDateNaissance());
        realisateur.setImdbId(dto.getImdbId());
        realisateur.setUrl(dto.getUrl());

        return realisateur;
    }

}