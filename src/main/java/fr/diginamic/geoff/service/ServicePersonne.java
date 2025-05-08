package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dao.ActeurDAO;
import fr.diginamic.geoff.dao.PersonneDAO;
import fr.diginamic.geoff.dao.RealisateurDAO;
import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.dto.PersonneDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.entity.Lieu;
import fr.diginamic.geoff.entity.Personne;
import fr.diginamic.geoff.entity.Realisateur;
import fr.diginamic.geoff.mapper.ActeurMapper;
import fr.diginamic.geoff.mapper.RealisateurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ServicePersonne {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicePersonne.class);

    private final ActeurMapper acteurMapper = new ActeurMapper();
    private final RealisateurMapper realisateurMapper = new RealisateurMapper();
    private final RealisateurDAO realisateurDAO;
    private final ActeurDAO acteurDAO;
    private final ServiceLieu serviceLieu;
    private final PersonneDAO personneDAO;

    public ServicePersonne(ServiceLieu serviceLieu, RealisateurDAO realisateurDAO, ActeurDAO acteurDAO, PersonneDAO personneDAO) {
        this.serviceLieu = serviceLieu;
        this.realisateurDAO = realisateurDAO;
        this.acteurDAO = acteurDAO;
        this.personneDAO = personneDAO;
    }

    /**
     * @param acteurDTO
     * @return
     */
    public Acteur getOrCreateFromActeurDTO(ActeurDTO acteurDTO) {
        if (acteurDTO == null) {
            return null;
        }


        Optional<Acteur> existingActeur = acteurDAO.findByImdbId(acteurDTO.getImdbId());
        if (existingActeur.isPresent()) {
            return existingActeur.get();
        }

        Optional<Personne> existingPersonne = personneDAO.findByImdbId(acteurDTO.getImdbId());
        if (existingPersonne.isPresent()) {
            Personne personne = existingPersonne.get();
            if (personne instanceof Acteur acteur) {
                return acteur;
            }
            return null;

        }

        Acteur newActeur = acteurMapper.mapToEntity(acteurDTO);

        Lieu lieu = null;
        if (acteurDTO.getNaissance() != null && acteurDTO.getNaissance().getLieuNaissance() != null) {
            lieu = serviceLieu.getOrCreateFromNaissance(acteurDTO.getNaissance());
            newActeur.setLieuxNaissance(lieu);
        }

        return acteurDAO.create(newActeur);
    }

    /**
     * @param personneDTO
     * @return
     */
    public Realisateur getOrCreateFromPersonneDTO(PersonneDTO personneDTO) {
        if (personneDTO == null) {
            return null;
        }

        Optional<Realisateur> existingRealisateur = realisateurDAO.findByImdbId(personneDTO.getImdbId());

        if (existingRealisateur.isPresent()) {
            return existingRealisateur.get();
        }

        Optional<Personne> existingPersonne = personneDAO.findByImdbId(personneDTO.getImdbId());
        if (existingPersonne.isPresent()) {
            Personne personne = existingPersonne.get();
            if (personne instanceof Realisateur realisateur) {
                return realisateur;
            }
            return null;
        }

        Realisateur newRealisateur = realisateurMapper.mapToEntity(personneDTO);

        Lieu lieu = null;
        if (personneDTO.getNaissance() != null && personneDTO.getNaissance().getLieuNaissance() != null) {
            lieu = serviceLieu.getOrCreateFromNaissance(personneDTO.getNaissance());
            newRealisateur.setLieuxNaissance(lieu);
        }

        return realisateurDAO.create(newRealisateur);
    }

}