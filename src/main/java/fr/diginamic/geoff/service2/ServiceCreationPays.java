package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dao.PaysDAO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.entity.Pays;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service dedicated to the creation of Pays entities from json Data nested inside a List of Film DTOs.
 * The Pays data is collected from :
 * - Films
 * - Lieux de Tournage
 * - Personnes (Acteurs and Realisateurs) Naissances
 * - > This information is nested in CastingPrincipal, RolesDTO, RealisateursDTO
 */
public class ServiceCreationPays {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceCreationPays.class);
    private final EntityManager em;
    private final List<FilmDTO> filmDTOList;
    private final PaysDAO paysDAO;
    private final ServicePays servicePays;

    public ServiceCreationPays(EntityManager em, List<FilmDTO> filmDTOList) {
        this.em = em;
        this.filmDTOList = filmDTOList;
        this.paysDAO = new PaysDAO(em);
        this.servicePays = new ServicePays(paysDAO);
    }

    /**
     * Main entry point to create all Pays entities from different sources
     * Uses a single EntityManager for all operations.
     */
    public void createPays() {
        LOGGER.info("Starting creation of Pays entities from all sources");

        LOGGER.info("Processing Pays from Film");
        createPaysFromFilm();
        LOGGER.info("Processing Pays from LieuTournage");
        createPaysFromLieuTournage();
        LOGGER.info("Processing Pays from Naissance sources");
        createPaysFromNaissance();
        LOGGER.info("Completed creation of Pays entities");

    }

    /**
     * Creates Pays entities from the LieuTournage (filming location) data
     * Each film's location is processed in its own transaction for better isolation.
     */
    private void createPaysFromLieuTournage() {

        for (FilmDTO film : filmDTOList) {
            if (film.getLieuTournage() == null) continue;

            em.getTransaction().begin();
            try {
                Pays pays = servicePays.getOrCreateFromLieuTournage(film.getLieuTournage());
                em.getTransaction().commit();
            }
//            catch (DuplicatePaysException e) {
//                em.getTransaction().rollback();
//                LOGGER.warn("Duplicate pays found for film {}: {}", film.getImdbId(), e.getMessage());
//            }
            catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
//                LOGGER.warn("Error processing lieu tournage for film {} : {}", film.getImdbId(), e.getMessage());
            }
        }

    }

    private void createPaysFromFilm() {

        for (FilmDTO filmDTO : filmDTOList) {
            if (filmDTO.getPays() == null) {
//                LOGGER.warn("Film {} has no Pays", filmDTO.getPays());
                continue;
            }
            try {
                Pays pays = servicePays.getOrCreateFromPaysDTO(filmDTO.getPays());
                em.getTransaction().commit();
            }
//            catch (DuplicatePaysException e) {
//                em.getTransaction().rollback();
//                LOGGER.warn("Duplicate pays found for film {}: {}", filmDTO.getImdbId(), e.getMessage());
//            }
            catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
//                LOGGER.warn("Error processing Pays for film {} : {}", filmDTO.getPays().getNom(), e.getMessage());
            }
        }
    }


    /**
     * Creates Pays entities from all Naissance (birth) data sources
     * Processes actors, roles, and directors by delegating to specialized methods.
     */
    private void createPaysFromNaissance() {

        // Process all sources of naissance data
        processActeurNaissances();
        processRoleActeurNaissances();
        processRealisateurNaissances();

    }

    /**
     * Process acteur naissances from casting principal
     */
    private void processActeurNaissances() {
        for (FilmDTO film : filmDTOList) {
            if (film.getCastingPrincipal() == null) {
//                LOGGER.warn("Film {} has no Acteur", film.getImdbId());
                continue;
            }

            film.getCastingPrincipal().stream()
                    .filter(acteur -> acteur != null && acteur.getNaissance() != null)
                    .forEach(acteur -> processNaissanceWithTransaction(acteur.getNaissance(), "acteur casting", film.getImdbId()));
        }
    }

    /**
     * Process acteur naissances from roles
     */
    private void processRoleActeurNaissances() {
        for (FilmDTO film : filmDTOList) {
            if (film.getRoles() == null) {
//                LOGGER.warn("Film {} has no Role:Acteur", film.getImdbId());
                continue;
            }

            film.getRoles().stream()
                    .filter(role -> role != null && role.getActeur() != null && role.getActeur().getNaissance() != null)
                    .forEach(role -> processNaissanceWithTransaction(role.getActeur().getNaissance(), "role acteur", film.getImdbId()));
        }
    }

    /**
     * Process realisateur naissances
     */
    private void processRealisateurNaissances() {
        for (FilmDTO film : filmDTOList) {
            if (film.getRealisateurs() == null) {
//                LOGGER.warn("Film {} has no realisateurs", film.getImdbId());
                continue;
            }
            film.getRealisateurs().stream()
                    .filter(real -> real != null && real.getNaissance() != null)
                    .forEach(real -> processNaissanceWithTransaction(real.getNaissance(), "realisateur", film.getImdbId()));
        }
    }

    /**
     * Process a single naissance with proper transaction management
     */
    private void processNaissanceWithTransaction(NaissanceDTO naissance, String sourceType, String filmId) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Pays pays = servicePays.getOrCreateFromNaissance(naissance);
            transaction.commit();
        }
//        catch (DuplicatePaysException e) {
//            transaction.rollback();
//            LOGGER.warn("Duplicate pays found for naissance {} : {}", naissance.getLieuNaissance(), e.getMessage());
//        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
//            LOGGER.warn("Error processing pays from {} naissance for film {} : {}",
//                    sourceType, filmId, e.getMessage());
        }
    }

}