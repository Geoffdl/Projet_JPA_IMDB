package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.entity.Film;
import fr.diginamic.geoff.entity.Role;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.RoleMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;
import java.util.stream.Stream;

public class RoleService implements EntityService<Role, RoleDTO> {
    EntityMapper<RoleDTO, Role> roleMapper = new RoleMapper();

    @Override
    public Role createEntity(RoleDTO dto, Film film, Acteur acteur) {
        Role role = roleMapper.mapToEntity(dto);
        role.setFilm(film);
        role.setActeur(acteur);
        // Set other relationships if needed
        return role;
    }

    @Override
    public List<Role> createEntityList(List<FilmDTO> filmDTOList) {
        List<RoleDTO> rolesDTOList = getList(filmDTOList);

        rolesDTOList = DTOUtils.removeDuplicatesByNaturalId(rolesDTOList); //remove duplicates

        List<Role> roleList = rolesDTOList.stream().map(p -> roleMapper.mapToEntity(p)).toList(); // map to simple entity

        return roleList;
    }


    @Override
    public List<RoleDTO> getList(List<FilmDTO> filmDTOList) {

        List<RoleDTO> roleDTOList = filmDTOList.stream()
                .flatMap(film -> {
                    List<RoleDTO> roles = film.getRoles();
                    if (roles == null) {
                        return Stream.empty();
                    }
                    return roles.stream();
                }).toList();

        return roleDTOList;
    }

    public List<Role> getRolesForFilmDTO(FilmDTO dto) {
        List<RoleDTO> roleDTOs = filmDTO.getRoles();
        if (roleDTOs == null) {
            return List.of();
        }
        return roleDTOs.stream()
                .map(roleDTO -> {
                    // Find the corresponding Acteur for this roleDTO (by ID or other unique field)
                    Acteur acteur = findActeurForRoleDTO(roleDTO, acteurs);
                    return createEntity(roleDTO, film, acteur);
                })
                .toList();
    }
}