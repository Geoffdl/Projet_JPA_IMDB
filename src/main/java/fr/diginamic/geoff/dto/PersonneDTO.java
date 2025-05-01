package fr.diginamic.geoff.dto;

/**
 * @author Geoff
 *
 * Data Transfer Object for JSON to POJO conversion of personnes
 *
 */
public class PersonneDTO
{
    private String id;
    private String identite;
    private String url;
    private NaissanceDTO naissance;

    /**
     * No args constructor
     */
    public PersonneDTO()
    {
    }


    /**
     * Gets id for the class PersonneDTO
     *
     * @return value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id for the class PersonneDTO.
     *
     * @param id value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets identite for the class PersonneDTO
     *
     * @return value of identite
     */
    public String getIdentite() {
        return identite;
    }

    /**
     * Sets identite for the class PersonneDTO.
     *
     * @param identite value of identite
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    /**
     * Gets url for the class PersonneDTO
     *
     * @return value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url for the class PersonneDTO.
     *
     * @param url value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets naissance for the class PersonneDTO
     *
     * @return value of naissance
     */
    public NaissanceDTO getNaissance() {
        return naissance;
    }

    /**
     * Sets naissance for the class PersonneDTO.
     *
     * @param naissance value of naissance
     */
    public void setNaissance(NaissanceDTO naissance) {
        this.naissance = naissance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonneDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", identite='").append(identite).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", naissance=").append(naissance);
        sb.append('}');
        return sb.toString();
    }
}