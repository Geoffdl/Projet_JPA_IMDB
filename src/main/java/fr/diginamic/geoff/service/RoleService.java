package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.Role;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.RoleMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class RoleService {
    EntityMapper<RoleDTO, Role> roleMapper = new RoleMapper();

    /**
     * Take a list of roleDTOS and returns a List of simple Roles without duplicates
     *
     * @param filmDTOS list of raw DTO
     * @return listOf Roles
     */
    public List<Role> createEntities(List<FilmDTO> filmDTOS) {

        List<RoleDTO> rolesDTOList = getList(filmDTOS);

        rolesDTOList = DTOUtils.removeDuplicatesByNaturalId(rolesDTOList); //remove duplicates

        List<Role> realisateurList = rolesDTOList.stream().map(p -> roleMapper.mapToEntity(p)).toList(); // map to simple entity

        return realisateurList;
    }

    /**
     * Generates a list of all roles across all films;
     *
     * @param filmDTOS raw list of film dtos
     * @return list of roles
     */
    private List<RoleDTO> getList(List<FilmDTO> filmDTOS) {
        return filmDTOS.stream().flatMap(d -> d.getRole().stream()).toList();
    }
}