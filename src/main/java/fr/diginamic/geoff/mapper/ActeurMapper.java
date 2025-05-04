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

    private void setIdentity(ActeurDTO acteurDTO, Acteur acteur){
        String[] identite = StringUtils.stringToArrayOfStrings(acteurDTO.getIdentite(), " ");
        acteur.setNom(identite[0]);
        acteur.setPrenom(identite[1]);
    }
    private void setTaille(ActeurDTO acteurDTO, Acteur acteur){
        acteur.setTaille(StringUtils.stringToFloat(acteurDTO.getTaille()));
    }
}