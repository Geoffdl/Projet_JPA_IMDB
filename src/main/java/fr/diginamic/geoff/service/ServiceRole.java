package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dao.RoleDAO;
import fr.diginamic.geoff.dto.RoleDTO;
import fr.diginamic.geoff.entity.Acteur;
import fr.diginamic.geoff.entity.Film;
import fr.diginamic.geoff.entity.Role;
import fr.diginamic.geoff.mapper.RoleMapper;

import java.util.Optional;

public class ServiceRole {
    private final RoleDAO roleDAO;
    private final RoleMapper roleMapper;

    public ServiceRole(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
        this.roleMapper = new RoleMapper();
    }

    /**
     *
     * @param roleDTO
     * @param film
     * @param acteur
     * @return
     */
    public Role getOrCreateRoles(RoleDTO roleDTO, Film film, Acteur acteur){
        if(roleDTO == null || film == null || acteur == null){return null;}

        Optional<Role> existingRole = roleDAO.findByFilmAndActeurAndPersonnage(film, acteur, roleDTO.getPersonnage());
        if(existingRole.isPresent()){
            return existingRole.get();
        }
        Role newRole = roleMapper.mapToEntity(roleDTO);
        newRole.setFilm(film);
        newRole.setActeur(acteur);
        return roleDAO.create(newRole);
    }
}