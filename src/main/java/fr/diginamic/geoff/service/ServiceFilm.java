package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dao.FilmDAO;
import fr.diginamic.geoff.dao.RoleDAO;
import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.PersonneDTO;
import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.*;
import fr.diginamic.geoff.mapper.FilmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceFilm {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFilm.class);

    private final FilmMapper filmMapper = new FilmMapper();
    private final FilmDAO filmDAO;
    private final RoleDAO roleDAO;
    private final ServiceRole serviceRole;
    private final ServiceLieu serviceLieu;
    private final ServicePersonne servicePersonne;
    private final ServicePays servicePays;
    private final ServiceLangue serviceLangue;
    private final ServiceGenre serviceGenre;

    public ServiceFilm(ServiceRole serviceRole, ServiceLieu serviceLieu, FilmDAO filmDAO, RoleDAO roleDAO, ServicePersonne servicePersonne, ServicePays servicePays, ServiceLangue serviceLangue, ServiceGenre serviceGenre) {
        this.serviceLieu = serviceLieu;
        this.filmDAO = filmDAO;
        this.roleDAO = roleDAO;
        this.servicePersonne = servicePersonne;
        this.servicePays = servicePays;
        this.serviceLangue = serviceLangue;
        this.serviceGenre = serviceGenre;
        this.serviceRole = serviceRole;
    }

    /**
     * Film owns :
     * Pays, Lieu, Genres, Langues, Acteurs, Realisateurs, Casting
     */

    public Film getOrCreateFilm(FilmDTO filmDTO) {
        if (filmDTO == null) {
            return null;
        }

        Optional<Film> existingFilm = filmDAO.findByImdbId(filmDTO.getImdbId());
        if (existingFilm.isPresent()) {
            return existingFilm.get();
        }

        Film newFilm = filmMapper.mapToEntity(filmDTO);

        setOrCreatePays(filmDTO, newFilm);
        setOrCreateLieu(filmDTO, newFilm);
        setOrCreateGenres(filmDTO, newFilm);
        setOrCreateLangues(filmDTO, newFilm);
        setOrCreateRealisateur(filmDTO, newFilm);
        setOrCreateCasting(filmDTO, newFilm);


        LOGGER.info("Film ajouté {} ", newFilm.getTitre());

        filmDAO.create(newFilm);

        setOrCreateRoles(filmDTO, newFilm);

        return newFilm;
    }

    private void setOrCreateRoles(FilmDTO filmDTO, Film newFilm) {
        if (filmDTO.getRoles() == null || filmDTO.getRoles().isEmpty()) {
            return;
        }
        List<Role> roleList = new ArrayList<>();
        for (RoleDTO roleDTO : filmDTO.getRoles()) {
            if (roleDTO == null || roleDTO.getActeur() == null) {
                continue;
            }
            Acteur acteur = servicePersonne.getOrCreateFromActeurDTO(roleDTO.getActeur());

            Role role = serviceRole.getOrCreateRoles(roleDTO, newFilm, acteur);


            if (acteur.getRoles() == null) {
                acteur.setRoles(new ArrayList<>());
            }
            acteur.getRoles().add(role);

            roleList.add(role);
        }
        newFilm.getRoles().addAll(roleList);
    }

    private void setOrCreatePays(FilmDTO filmDTO, Film newFilm) {
        if (filmDTO.getPays() == null) {
            return;
        }
        Pays pays = servicePays.getOrCreateFromPaysDTO(filmDTO.getPays());
        newFilm.setPays(pays);

    }

    private void setOrCreateLieu(FilmDTO filmDTO, Film newFilm) {
        if (filmDTO.getLieuTournage() == null) {
            return;
        }
        Lieu lieu = serviceLieu.getOrCreateFromFilmDTO(filmDTO);
        newFilm.setLieuTournage(lieu);


    }

    private void setOrCreateGenres(FilmDTO filmDTO, Film newFilm) {
        List<String> genreFromDTO = new ArrayList<>();
        if (filmDTO.getGenres() == null) {
            return;
        }
        genreFromDTO = filmDTO.getGenres();
        List<Genre> genreList = new ArrayList<>();
        for (String genre : genreFromDTO) {
            genreList.add(serviceGenre.getOrCreateFromFilmDTO(genre));
        }
        newFilm.setGenres(genreList);

    }

    private void setOrCreateLangues(FilmDTO filmDTO, Film newFilm) {
        List<String> languesFromDTO = new ArrayList<>();
        if (filmDTO.getLangue() != null) {
            languesFromDTO.add(filmDTO.getLangue());
            List<Langue> langueList = new ArrayList<>();

            languesFromDTO.forEach(l -> langueList.add(serviceLangue.getOrCreateFromFilmDTO(filmDTO)));

            newFilm.setLangues(langueList);
        }
    }


    private void setOrCreateRealisateur(FilmDTO filmDTO, Film newFilm) {
        List<PersonneDTO> realisateurDTOList = new ArrayList<>();
        if (filmDTO.getRealisateurs() != null) {
            realisateurDTOList = filmDTO.getRealisateurs();
            List<Realisateur> realisateurList = new ArrayList<>();
            for (PersonneDTO real : realisateurDTOList) {
                realisateurList.add(servicePersonne.getOrCreateFromPersonneDTO(real));
            }
            newFilm.setRealisateurs(realisateurList);
        }
    }

    private void setOrCreateCasting(FilmDTO filmDTO, Film newFilm) {
        List<ActeurDTO> acteurDTOList = new ArrayList<>();
        if (filmDTO.getCastingPrincipal() != null) {
            acteurDTOList = filmDTO.getCastingPrincipal();
            List<Acteur> acteurList = new ArrayList<>();
            for (ActeurDTO acteurDTO : acteurDTOList) {
                acteurList.add(servicePersonne.getOrCreateFromActeurDTO(acteurDTO));
            }
            newFilm.setActeurs(acteurList);
        }
    }
}