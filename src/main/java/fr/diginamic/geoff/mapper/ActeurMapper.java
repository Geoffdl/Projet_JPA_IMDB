package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.utils.StringUtils;

public class ActeurMapper implements EntityMapper<ActeurDTO, Acteur> {

    @Override
    public Acteur mapToEntity(ActeurDTO dto) {

        Acteur acteur = new Acteur();
        acteur.setIdentite(dto.getIdentite());
        setTaille(dto, acteur);
        acteur.setDateNaissance(dto.getNaissance().getDateNaissance());
        acteur.setImdbId(dto.getImdbId());
        acteur.setUrl(dto.getUrl());

        return acteur;
    }

    /**
     * Converts taille to float before mapping
     * @param acteurDTO data source
     * @param acteur the target object attribute with conversion through utility method
     */
    private void setTaille(ActeurDTO acteurDTO, Acteur acteur) {
        acteur.setTaille(StringUtils.stringToFloat(acteurDTO.getTaille()));
    }
}