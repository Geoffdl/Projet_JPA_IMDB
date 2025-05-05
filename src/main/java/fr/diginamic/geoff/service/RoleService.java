package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.Role;
import fr.diginamic.geoff.mapper.EntityMapper;
import fr.diginamic.geoff.mapper.RoleMapper;
import fr.diginamic.geoff.utils.DTOUtils;

import java.util.List;
import java.util.stream.Stream;

public class RoleService implements EntityService<Role, RoleDTO> {
    EntityMapper<RoleDTO, Role> roleMapper = new RoleMapper();

    @Override
    public List<Role> createEntityList(List<FilmDTO> filmDTOList) {
        List<RoleDTO> rolesDTOList = getList(filmDTOList);

        rolesDTOList = DTOUtils.removeDuplicatesByNaturalId(rolesDTOList); //remove duplicates

        List<Role> roleList = rolesDTOList.stream().map(p -> roleMapper.mapToEntity(p)).toList(); // map to simple entity

        return roleList;
    }

    /**
     *
     * @param filmDTOList data source
     * @return
     */
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
}