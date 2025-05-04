package fr.diginamic.geoff.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Geoff
 * <p>
 * Data Transfer Object for JSON to POJO conversion of acteurs
 */
public class ActeurDTO extends PersonneDTO {

    @JsonProperty("height")
    private String taille;

    public ActeurDTO() {
    }

    /**
     * Gets height for the class ActeurDTO
     *
     * @return value of height
     */
    public String getTaille() {
        return taille;
    }

    /**
     * Sets height for the class ActeurDTO.
     *
     * @param taille value of height
     */
    public void setTaille(String taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActeurDTO{");
        sb.append("height='").append(taille).append('\'');
        sb.append('}');
        return sb.toString();
    }


}