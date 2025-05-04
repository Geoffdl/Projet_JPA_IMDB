package fr.diginamic.geoff.dto;

/**
 * @author Geoff
 * <p>
 * Data Transfer Object for JSON to POJO conversion of pays
 */
public class PaysDTO implements NaturalIdentifiable{
    private String nom;
    private String url;


    public PaysDTO() {
    }

    /**
     * Gets nom for the class PaysDto
     *
     * @return value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom for the class PaysDto.
     *
     * @param nom value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets url for the class PaysDto
     *
     * @return value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url for the class PaysDto.
     *
     * @param url value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PaysDto{");
        sb.append("nom='").append(nom).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getNaturalId() {
        return nom;
    }
}