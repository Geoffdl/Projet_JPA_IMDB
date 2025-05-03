package fr.diginamic.geoff.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.diginamic.geoff.dto.deserializer.FloatDeserializer;
import fr.diginamic.geoff.dto.deserializer.YearDeserializer;

import java.time.Year;
import java.util.List;
/**
 * @author Geoff
 *
 * Data Transfer Object for JSON to POJO conversion of films
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDTO
{
    private String id;
    private PaysDTO pays;
    private String nom;
    private String url;

    /**
     * Handles conversion of json string to Float
     */
    @JsonDeserialize(using = FloatDeserializer.class)
    private Float rating;

    private String plot;
    private String langue;
    private LieuTournageDTO lieuTournage;
    private List<PersonneDTO> realisateurs;
    private List<ActeurDTO> castingPrincipal;

    /**
     * Handles conversion of json string to Year
     */
    @JsonDeserialize(using = YearDeserializer.class)
    private Year anneeSortie;

    private List<RoleDTO> role;
    private List<String> genres;

    /**
     * No arg constructor
     */
    public FilmDTO()
    {
    }

    /**
     * Gets id for the class FilmDTO
     *
     * @return value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id for the class FilmDTO.
     *
     * @param id value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets pays for the class FilmDTO
     *
     * @return value of pays
     */
    public PaysDTO getPays() {
        return pays;
    }

    /**
     * Sets pays for the class FilmDTO.
     *
     * @param pays value of pays
     */
    public void setPays(PaysDTO pays) {
        this.pays = pays;
    }

    /**
     * Gets nom for the class FilmDTO
     *
     * @return value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom for the class FilmDTO.
     *
     * @param nom value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets url for the class FilmDTO
     *
     * @return value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url for the class FilmDTO.
     *
     * @param url value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets rating for the class FilmDTO
     *
     * @return value of rating
     */
    public Float getRating() {
        return rating;
    }

    /**
     * Sets rating for the class FilmDTO.
     *
     * @param rating value of rating
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }

    /**
     * Gets plot for the class FilmDTO
     *
     * @return value of plot
     */
    public String getPlot() {
        return plot;
    }

    /**
     * Sets plot for the class FilmDTO.
     *
     * @param plot value of plot
     */
    public void setPlot(String plot) {
        this.plot = plot;
    }

    /**
     * Gets langue for the class FilmDTO
     *
     * @return value of langue
     */
    public String getLangue() {
        return langue;
    }

    /**
     * Sets langue for the class FilmDTO.
     *
     * @param langue value of langue
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Gets lieuTouraage for the class FilmDTO
     *
     * @return value of lieuTouraage
     */
    public LieuTournageDTO getLieuTournage() {
        return lieuTournage;
    }

    /**
     * Sets lieuTouraage for the class FilmDTO.
     *
     * @param lieuTournage value of lieuTouraage
     */
    public void setLieuTournage(LieuTournageDTO lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    /**
     * Gets realisateurs for the class FilmDTO
     *
     * @return value of realisateurs
     */
    public List<PersonneDTO> getRealisateurs() {
        return realisateurs;
    }

    /**
     * Sets realisateurs for the class FilmDTO.
     *
     * @param realisateurs value of realisateurs
     */
    public void setRealisateurs(List<PersonneDTO> realisateurs) {
        this.realisateurs = realisateurs;
    }

    /**
     * Gets castingPrincipal for the class FilmDTO
     *
     * @return value of castingPrincipal
     */
    public List<ActeurDTO> getCastingPrincipal() {
        return castingPrincipal;
    }

    /**
     * Sets castingPrincipal for the class FilmDTO.
     *
     * @param castingPrincipal value of castingPrincipal
     */
    public void setCastingPrincipal(List<ActeurDTO> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    /**
     * Gets anneeSortie for the class FilmDTO
     *
     * @return value of anneeSortie
     */
    public Year getAnneeSortie() {
        return anneeSortie;
    }

    /**
     * Sets anneeSortie for the class FilmDTO.
     *
     * @param anneeSortie value of anneeSortie
     */
    public void setAnneeSortie(Year anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    /**
     * Gets role for the class FilmDTO
     *
     * @return value of role
     */
    public List<RoleDTO> getRole() {
        return role;
    }

    /**
     * Sets role for the class FilmDTO.
     *
     * @param role value of role
     */
    public void setRole(List<RoleDTO> role) {
        this.role = role;
    }

    /**
     * Gets genres for the class FilmDTO
     *
     * @return value of genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Sets genres for the class FilmDTO.
     *
     * @param genres value of genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FilmDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", pays=").append(pays);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", plot='").append(plot).append('\'');
        sb.append(", langue='").append(langue).append('\'');
        sb.append(", lieuTouraage=").append(lieuTournage);
        sb.append(", realisateurs=").append(realisateurs);
        sb.append(", castingPrincipal=").append(castingPrincipal);
        sb.append(", anneeSortie=").append(anneeSortie);
        sb.append(", role=").append(role);
        sb.append(", genres=").append(genres);
        sb.append('}');
        return sb.toString();
    }
}