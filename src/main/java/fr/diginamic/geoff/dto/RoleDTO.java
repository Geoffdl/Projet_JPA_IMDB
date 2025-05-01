package fr.diginamic.geoff.dto;

/**
 * @author Geoff
 *
 * Data Transfer Object for JSON to POJO conversion of roles
 *
 */
public class RoleDTO
{
    private String characterName;
    private ActeurDTO acteur;


    public RoleDTO()
    {
    }

    /**
     * Gets characterName for the class RoleDTO
     *
     * @return value of characterName
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Sets characterName for the class RoleDTO.
     *
     * @param characterName value of characterName
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
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
        sb.append("characterName='").append(characterName).append('\'');
        sb.append(", acteur=").append(acteur);
        sb.append('}');
        return sb.toString();
    }
}