package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.utils.StringUtils;

public class ActeurMapper implements EntityMapper<ActeurDTO, Acteur> {

    @Override
    public Acteur mapToEntity(ActeurDTO dto) {

        Acteur acteur = new Acteur();
        setIdentity(dto, acteur);
        setTaille(dto, acteur);
        acteur.setDateNaissance(dto.getNaissance().getDateNaissance());
        acteur.setImdbId(dto.getImdbId());
        acteur.setUrl(dto.getUrl());

        return acteur;
    }

    private void setIdentity(ActeurDTO acteurDTO, Acteur acteur) {
        //TODO null handling and diff length cases
        String[] identite = new String[2];
        identite = StringUtils.stringToArrayOfStrings(acteurDTO.getIdentite(), " ");
        if (identite.length >= 2) {
            acteur.setNom(identite[0]);
            acteur.setPrenom(identite[1]);
        } else{
            acteur.setPrenom("");
            acteur.setNom("");
        }
    }

    private void setTaille(ActeurDTO acteurDTO, Acteur acteur) {
        acteur.setTaille(StringUtils.stringToFloat(acteurDTO.getTaille()));
    }
}