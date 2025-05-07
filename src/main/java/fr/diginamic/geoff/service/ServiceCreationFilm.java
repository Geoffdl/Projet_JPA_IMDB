package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dao.*;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.entity.Film;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ServiceCreationFilm {
    private final EntityManager em;
    private final List<FilmDTO> filmDTOList;
    private final FilmService serviceFilm;
    private final ServiceRole serviceRole;
    private final ServicePersonne servicePersonne;
    private final ServicePays servicePays;
    private final ServiceLangue serviceLangue;
    private final ServiceLieu serviceLieu;
    private final ServiceGenre serviceGenre;
    private final FilmDAO filmDAO;
    private final RoleDAO roleDAO;

    public ServiceCreationFilm(EntityManager em, List<FilmDTO> filmDTOList) {
        this.em = em;
        this.filmDTOList = filmDTOList;
        this.serviceRole = new ServiceRole(new RoleDAO(em));
        this.serviceGenre = new ServiceGenre(new GenreDAO(em));
        this.serviceLangue = new ServiceLangue(new LangueDAO(em));
        this.servicePays = new ServicePays(new PaysDAO(em));
        this.serviceLieu = new ServiceLieu(servicePays, new LieuDAO(em));
        RealisateurDAO realisateurDAO = new RealisateurDAO(em);
        ActeurDAO acteurDAO = new ActeurDAO(em);
        PersonneDAO personneDAO = new PersonneDAO(em);
        this.roleDAO = new RoleDAO(em);
        this.servicePersonne = new ServicePersonne(serviceLieu, realisateurDAO, acteurDAO, personneDAO);
        this.filmDAO = new FilmDAO(em);
        this.serviceFilm = new FilmService(serviceRole, serviceLieu, filmDAO, roleDAO, servicePersonne, servicePays, serviceLangue,
                serviceGenre);
    }

    public void createFilm() {
        for (FilmDTO filmDTO : filmDTOList) {
            if (filmDTO == null) {
                continue;
            }
            filmDAO.runInTransaction(() -> {
                Film film = serviceFilm.getOrCreateFilm(filmDTO);
            }, em);
        }
    }
}