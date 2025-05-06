package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.*;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.FilmMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class FilmService implements EntityService<Film, FilmDTO> {

    EntityMapper<FilmDTO, Film> filmMapper = new FilmMapper();

    private final RoleService roleService;
    private final GenreService genreService;
    private final LangueService langueService;
    private final RealisateurService realisateurService;
    private final ActeurService acteurService;
    private final LieuService lieuService;
    private final PaysService paysService;

    //TODO Relationship add List : Genre, Langues, Realisateurs, Casting, Roles
    //TODO fkey lieu pays

    public FilmService(GenreService genreService, LangueService langueService, RealisateurService realisateurService,
                       ActeurService acteurService, LieuService lieuService, PaysService paysService, RoleService roleService) {
        this.genreService = genreService;
        this.langueService = langueService;
        this.realisateurService = realisateurService;
        this.acteurService = acteurService;
        this.lieuService = lieuService;
        this.paysService = paysService;
        this.roleService = roleService;
    }

    @Override
    public Film createEntity(FilmDTO filmDTO) {

        Film film = filmMapper.mapToEntity(filmDTO);

        //Many to Many
        film.setGenres(mapGenres(filmDTO));
        film.setLangues(mapLangues(filmDTO));
        film.setRealisateurs(mapRealisateurs(filmDTO));
        film.setActeurs(mapActeurs(filmDTO));

        //Many to One
        film.setLieuTournage(mapLieu(filmDTO));
        film.setPays(mapPays(filmDTO));

        //Role
        for (Role role : roles) {
            if (role.getActeur() == null && filmDTO.getRoles() != null) {
                // Find matching RoleDTO to get its actor
                for (RoleDTO roleDTO : filmDTO.getRoles()) {
                    if (roleDTO.getPersonnage().equals(role.getPersonnage())) {
                        // Find or create the corresponding Acteur entity
                        Acteur acteur = acteurService.findOrCreateActeurFromDTO(roleDTO.getActeur());
                        role.setActeur(acteur);
                        break;
                    }
                }
            }
        }
        film.setRoles(mapRoles(filmDTO));
        return film;
    }

    @Override
    public List<Film> createEntityList(List<FilmDTO> filmDTOList) {
        filmDTOList = DTOUtils.removeDuplicatesByNaturalId(filmDTOList); //remove duplicates


        List<Film> filmList = filmDTOList.stream().map(dto -> {
            try {
                return createEntity(dto);
            } catch (Exception e) {
                //log
                return null;
            }
        }).toList();

        return filmList;
    }

    private Pays mapPays(FilmDTO dto) {
        return paysService.getPaysForFilmDTO(dto);
    }

    private Lieu mapLieu(FilmDTO dto) {
        return lieuService.getLieuForFilmDTO(dto);
    }

    private List<Role> mapRoles(FilmDTO dto) {
        return roleService.getRolesForFilmDTO(dto);
    }

    private List<Acteur> mapActeurs(FilmDTO dto) {
        return acteurService.getActeursForFilmDTO(dto);
    }

    private List<Realisateur> mapRealisateurs(FilmDTO dto) {
        return realisateurService.getRealisateursForFilmDTO(dto);
    }

    private List<Langue> mapLangues(FilmDTO dto) {
        return langueService.getLanguesForFilmDTO(dto);
    }


    private List<Genre> mapGenres(FilmDTO dto) {
        return genreService.getGenresForFilmDTO(dto);
    }

    //not needed in this case
    @Override
    public List<FilmDTO> getList(List<FilmDTO> filmDTOList) {
        return List.of();
    }
}