package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.PersonneDTO;
import fr.diginamic.geoff.entity.Realisateur;

public class PersonneMapper implements EntityMapper<PersonneDTO, Realisateur> {
    @Override
    public Realisateur mapToEntity(PersonneDTO dto) {

        Realisateur realisateur = new Realisateur();

//        setIdentity(dto, realisateur);
        realisateur.setIdentite(dto.getIdentite());
        realisateur.setDateNaissance(dto.getNaissance().getDateNaissance());
        realisateur.setImdbId(dto.getImdbId());
        realisateur.setUrl(dto.getUrl());

        return realisateur;
    }

//    //TODO null handling and diff length cases
//    private void setIdentity(PersonneDTO realisateurDTO, Realisateur realisateur) {
//        String[] identite = StringUtils.stringToArrayOfStrings(realisateurDTO.getIdentite(), " ");
//        if(identite.length >=2){
//
//            realisateur.setNom(identite[0]);
//            realisateur.setPrenom(identite[1]);
//        } else{
//            realisateur.setNom("");
//            realisateur.setPrenom("");
//        }
//    }


}