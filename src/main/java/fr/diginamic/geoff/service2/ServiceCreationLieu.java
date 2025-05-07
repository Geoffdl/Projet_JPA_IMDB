package fr.diginamic.geoff.service2;

import fr.diginamic.geoff.dao.LieuDAO;
import fr.diginamic.geoff.dao.PaysDAO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.NaissanceDTO;
import fr.diginamic.geoff.entity.Lieu;
import fr.diginamic.geoff.entity.Pays;
import fr.diginamic.geoff.utils.NaissanceUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;


public class ServiceCreationLieu {
    private final List<FilmDTO> filmDTOList;
    private final EntityManager em;
    private final LieuDAO lieuDAO;
    private final PaysDAO paysDAO;
    private final ServiceLieu serviceLieu;
    private final ServicePays servicePays;

    public ServiceCreationLieu(EntityManager em, List<FilmDTO> filmDTOList) {
        this.em = em;
        this.filmDTOList = filmDTOList;
        this.lieuDAO = new LieuDAO(em);
        this.paysDAO = new PaysDAO(em);
        this.servicePays = new ServicePays(paysDAO);
        this.serviceLieu = new ServiceLieu(servicePays, lieuDAO);
    }

    public void createLieu() {

        createLieuFromTournage();
        createLieuFromNaissance();
    }

    private void createLieuFromNaissance() {

        List<NaissanceDTO> naissanceFromAllSources = NaissanceUtils.collectAllNaissancesAsList(filmDTOList);


        for (NaissanceDTO naissance : naissanceFromAllSources) {
            if (naissance.getLieuNaissance() == null) {
                continue;
            }

            runInTransaction(() -> {
                Pays pays = servicePays.getOrCreateFromNaissance(naissance);
                Lieu lieu = serviceLieu.getOrCreateFromNaissance(naissance);
            });


        }
    }


    private void createLieuFromTournage() {

        for (FilmDTO film : filmDTOList) {
            if (film.getLieuTournage() == null) continue;

            runInTransaction(() -> {
                Pays pays = servicePays.getOrCreateFromLieuTournage(film.getLieuTournage());

                Lieu lieu = serviceLieu.getOrCreateFromFilmDTO(film);
            });

        }
    }

    public void runInTransaction(Runnable action) {
        EntityTransaction transaction = em.getTransaction();
        try {
            if (!transaction.isActive()) transaction.begin();
            action.run();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

}