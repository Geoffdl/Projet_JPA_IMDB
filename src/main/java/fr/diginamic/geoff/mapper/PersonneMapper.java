package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.PersonneDTO;
import fr.diginamic.geoff.entity.Realisateur;
import fr.diginamic.geoff.utils.StringUtils;

public class PersonneMapper implements EntityMapper<PersonneDTO, Realisateur> {
    @Override
    public Realisateur mapToEntity(PersonneDTO dto) {

        Realisateur realisateur = new Realisateur();

        setIdentity(dto, realisateur);

        realisateur.setDateNaissance(dto.getNaissance().getDateNaissance());
        realisateur.setImdbId(dto.getId());
        realisateur.setUrl(dto.getUrl());

        return realisateur;
    }

    private void setIdentity(PersonneDTO realisateurDTO, Realisateur realisateur) {
        String[] identite = StringUtils.stringToArrayOfStrings(realisateurDTO.getIdentite(), " ");
        realisateur.setNom(identite[0]);
        realisateur.setPrenom(identite[1]);
    }


}