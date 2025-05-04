package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.Role;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.RoleMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;

public class RoleService implements EntityService<Role, RoleDTO> {
    EntityMapper<RoleDTO, Role> roleMapper = new RoleMapper();

    @Override
    public List<Role> createEntityList(List<FilmDTO> filmDTOList) {
        List<RoleDTO> rolesDTOList = getList(filmDTOList);

        rolesDTOList = DTOUtils.removeDuplicatesByNaturalId(rolesDTOList); //remove duplicates

        List<Role> realisateurList = rolesDTOList.stream().map(p -> roleMapper.mapToEntity(p)).toList(); // map to simple entity

        return realisateurList;
    }

    //TODO Null handling
    @Override
    public List<RoleDTO> getList(List<FilmDTO> filmDTOList) {

        return filmDTOList.stream().flatMap(d -> d.getRole().stream()).toList();
    }

}