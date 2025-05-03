package fr.diginamic.geoff.mapper;

import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.Role;

public class RoleMapper implements EntityMapper<RoleDTO, Role> {
    @Override
    public Role mapToEntity(RoleDTO dto) {

        Role role = new Role();

        role.setPersonnage(dto.getCharacterName());

        return role;
    }
}