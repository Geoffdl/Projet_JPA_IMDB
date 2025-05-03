package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.utils.StringUtils;

public class ActeurMapper implements EntityMapper<ActeurDTO, Acteur> {

    @Override
    public Acteur mapToEntity(ActeurDTO dto) {

        Acteur acteur = new Acteur();
        acteur.setTaille(StringUtils.stringToFloat(dto.getHeight()));

        String[] identite = StringUtils.stringToArrayOfStrings(dto.getIdentite(), " ");
        acteur.setNom(identite[0]);
        acteur.setPrenom(identite[1]);
        acteur.setDateNaissance(dto.getNaissance().getDateNaissance());
        acteur.setImdbId(dto.getId());
        acteur.setUrl(dto.getUrl());

        return acteur;
    }

}