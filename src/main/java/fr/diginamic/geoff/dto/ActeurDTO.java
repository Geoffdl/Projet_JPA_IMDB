package fr.diginamic.geoff.dto;

/**
 * @author Geoff
 *
 * Data Transfer Object for JSON to POJO conversion of acteurs
 *
 */
public class ActeurDTO extends PersonneDTO
{

    private String height;

    public ActeurDTO() {
    }

    /**
     * Gets height for the class ActeurDTO
     *
     * @return value of height
     */
    public String getHeight() {
        return height;
    }

    /**
     * Sets height for the class ActeurDTO.
     *
     * @param height value of height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActeurDTO{");
        sb.append("height='").append(height).append('\'');
        sb.append('}');
        return sb.toString();
    }
}