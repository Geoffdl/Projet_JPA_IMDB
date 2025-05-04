package fr.diginamic.geoff.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Geoff
 * <p>
 * Data Transfer Object for JSON to POJO conversion of roles
 */
public class RoleDTO implements NaturalIdentifiable{
    @JsonProperty("characterName")
    private String personnage;

    private ActeurDTO acteur;

    public RoleDTO() {
    }

    /**
     * Gets characterName for the class RoleDTO
     *
     * @return value of characterName
     */
    public String getPersonnage() {
        return personnage;
    }

    /**
     * Sets characterName for the class RoleDTO.
     *
     * @param personnage value of characterName
     */
    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

    /**
     * Gets acteur for the class RoleDTO
     *
     * @return value of acteur
     */
    public ActeurDTO getActeur() {
        return acteur;
    }

    /**
     * Sets acteur for the class RoleDTO.
     *
     * @param acteur value of acteur
     */
    public void setActeur(ActeurDTO acteur) {
        this.acteur = acteur;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleDTO{");
        sb.append("characterName='").append(personnage).append('\'');
        sb.append(", acteur=").append(acteur);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getNaturalId() {
        return personnage;
    }
}