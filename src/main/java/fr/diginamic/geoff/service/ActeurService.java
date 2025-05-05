package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.ActeurDTO;
import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.mapper.ActeurMapper;

import java.util.List;
import java.util.stream.Stream;

/**
 * This service class gets acteurs from CastingPrincipal and from RoleDTO and maps it into Acteur entity
 */
public class ActeurService implements EntityService<Acteur, ActeurDTO> {

    private final ActeurMapper mapper = new ActeurMapper();
//    EntityMapper<ActeurDTO, Acteur> acteurMapper = new ActeurMapper();

    @Override
    public List<Acteur> createEntityList(List<FilmDTO> filmDTOList) {

        List<Acteur> acteurDTOListFromCasting = createEntityListFromCasting(filmDTOList);
        List<Acteur> acteurDTOListFromRole = createEntityListFromRoles(filmDTOList);

        List<Acteur> concatList = Stream.concat(acteurDTOListFromRole.stream(), acteurDTOListFromCasting.stream()).distinct().toList();

        return concatList;
    }

    @Override
    public List<ActeurDTO> getList(List<FilmDTO> filmDTOList) {
        List<ActeurDTO> acteurDTOFromCasting = filmDTOList.stream().flatMap(d -> d.getCastingPrincipal().stream()).toList();

        return acteurDTOFromCasting;
    }

    /**
     *
     * @param filmDTOList
     * @return
     */
    public List<ActeurDTO> getListFromRole(List<FilmDTO> filmDTOList) {

        List<ActeurDTO> acteurDTOFromRole = filmDTOList.stream()
                .flatMap(film -> {
                    List<RoleDTO> roles = film.getRoles();
                    if (roles == null) {
                        return Stream.empty();
                    }
                    return roles.stream();
                })
                .map(role -> role.getActeur()).toList();
        return acteurDTOFromRole;
    }

    /**
     *
     * @param filmDTOList
     * @return
     */
    public List<Acteur> createEntityListFromCasting(List<FilmDTO> filmDTOList) {
        List<ActeurDTO> acteurDTOList = getList(filmDTOList);

        return acteurDTOList.stream().map(a -> mapper.mapToEntity(a)).distinct().toList();
    }

    /**
     *
     * @param filmDTOList
     * @return
     */
    private List<Acteur> createEntityListFromRoles(List<FilmDTO> filmDTOList) {
        List<ActeurDTO> acteurDTOList = getListFromRole(filmDTOList);

        return acteurDTOList.stream().map(a -> mapper.mapToEntity(a)).distinct().toList();
    }
}