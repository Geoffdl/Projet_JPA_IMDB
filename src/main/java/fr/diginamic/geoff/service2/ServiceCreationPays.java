package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dao.PaysDAO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.exception.DuplicatePaysException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
    private final EntityManagerFactory emf;
    private final List<FilmDTO> filmDTOList;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceCreationPays.class);


    /**
     * Constructor for Pays creation Service
     *
     * @param filmDTOList         data source
     * @param persistenceUnitName persistence config unit name
     */
    public ServiceCreationPays(List<FilmDTO> filmDTOList, String persistenceUnitName) {
        this.filmDTOList = filmDTOList;
        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    /**
     * Main entry point to create all Pays entities from different sources
     * Uses a single EntityManager for all operations.
     */
    public void createPays() {
        LOGGER.info("Starting creation of Pays entities from all sources");
        try (EntityManager em = emf.createEntityManager()) {

            LOGGER.info("Processing Pays from Film");
            createPaysFromFilm(em);
            LOGGER.info("Processing Pays from LieuTournage");
            createPaysFromLieuTournage(em);
            LOGGER.info("Processing Pays from Naissance sources");
            createPaysFromNaissance(em);
            LOGGER.info("Completed creation of Pays entities");

        } catch (Exception e) {
            LOGGER.error("Error during Pays creation: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Creates Pays entities from the LieuTournage (filming location) data
     * Each film's location is processed in its own transaction for better isolation.
     *
     * @param em EntityManager to use for persistence operations
     */
    private void createPaysFromLieuTournage(EntityManager em) {

        PaysDAO paysDAO = new PaysDAO(em);
        ServicePays paysService = new ServicePays(paysDAO);

        for (FilmDTO film : filmDTOList) {
            if (film.getLieuTournage() == null) continue;

            em.getTransaction().begin();
            try {
                Pays pays = paysService.getOrCreateFromLieuTournage(film.getLieuTournage());
                em.getTransaction().commit();
            } catch (DuplicatePaysException e) {
                em.getTransaction().rollback();
                LOGGER.warn("Duplicate pays found for film {}: {}", film.getImdbId(), e.getMessage());
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                LOGGER.warn("Error processing lieu tournage for film {} : {}", film.getImdbId(), e.getMessage());
            }
        }

    }

    private void createPaysFromFilm(EntityManager em) {
        PaysDAO paysDAO = new PaysDAO(em);
        ServicePays paysService = new ServicePays(paysDAO);

        for (FilmDTO filmDTO : filmDTOList) {
            if (filmDTO.getPays() == null) {
                LOGGER.warn("Film {} has no Pays", filmDTO.getPays());
                continue;
            }
            try {
                Pays pays = paysService.getOrCreateFromFilmDTO(filmDTO.getPays());
                em.getTransaction().commit();
            } catch (DuplicatePaysException e) {
                em.getTransaction().rollback();
                LOGGER.warn("Duplicate pays found for film {}: {}", filmDTO.getImdbId(), e.getMessage());
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                LOGGER.warn("Error processing Pays for film {} : {}", filmDTO.getPays().getNom(), e.getMessage());
            }
        }
    }


    /**
     * Creates Pays entities from all Naissance (birth) data sources
     * Processes actors, roles, and directors by delegating to specialized methods.
     *
     * @param em EntityManager to use for persistence operations
     */
    private void createPaysFromNaissance(EntityManager em) {


        PaysDAO paysDAO = new PaysDAO(em);
        ServicePays paysService = new ServicePays(paysDAO);

        // Process all sources of naissance data
        processActeurNaissances(em, paysService);
        processRoleActeurNaissances(em, paysService);
        processRealisateurNaissances(em, paysService);

    }

    /**
     * Process acteur naissances from casting principal
     */
    private void processActeurNaissances(EntityManager em, ServicePays paysService) {
        for (FilmDTO film : filmDTOList) {
            if (film.getCastingPrincipal() == null) {
                LOGGER.warn("Film {} has no Acteur", film.getImdbId());
                continue;
            }

            film.getCastingPrincipal().stream()
                    .filter(acteur -> acteur != null && acteur.getNaissance() != null)
                    .forEach(acteur -> processNaissanceWithTransaction(em, paysService, acteur.getNaissance(), "acteur casting", film.getImdbId()));
        }
    }

    /**
     * Process acteur naissances from roles
     */
    private void processRoleActeurNaissances(EntityManager em, ServicePays paysService) {
        for (FilmDTO film : filmDTOList) {
            if (film.getRoles() == null) {
                LOGGER.warn("Film {} has no Role:Acteur", film.getImdbId());
                continue;
            }

            film.getRoles().stream()
                    .filter(role -> role != null && role.getActeur() != null && role.getActeur().getNaissance() != null)
                    .forEach(role -> processNaissanceWithTransaction(em, paysService, role.getActeur().getNaissance(), "role acteur", film.getImdbId()));
        }
    }

    /**
     * Process realisateur naissances
     */
    private void processRealisateurNaissances(EntityManager em, ServicePays paysService) {
        for (FilmDTO film : filmDTOList) {
            if (film.getRealisateurs() == null) {
                LOGGER.warn("Film {} has no realisateurs", film.getImdbId());
                continue;
            }
            film.getRealisateurs().stream()
                    .filter(real -> real != null && real.getNaissance() != null)
                    .forEach(real -> processNaissanceWithTransaction(em, paysService, real.getNaissance(), "realisateur", film.getImdbId()));
        }
    }

    /**
     * Process a single naissance with proper transaction management
     */
    private void processNaissanceWithTransaction(EntityManager em, ServicePays paysService, NaissanceDTO naissance, String sourceType, String filmId) {

        em.getTransaction().begin();
        try {
            Pays pays = paysService.getOrCreateFromNaissance(naissance);
            em.getTransaction().commit();
        } catch (DuplicatePaysException e) {
            em.getTransaction().rollback();
            LOGGER.warn("Duplicate pays found for naissance {} : {}", naissance.getLieuNaissance(), e.getMessage());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.warn("Error processing pays from {} naissance for film {} : {}",
                    sourceType, filmId, e.getMessage());
        }
    }

    /**
     * Clean up resources when done with all operations
     */
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}