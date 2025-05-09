package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", length = 11)
    private Long filmId;

    @Column(name = "id_imdb", length = 12)
    private String imdbId;
    @Column(name = "titre", length = 255)
    private String titre;
    @Column(name = "annee")
    private Year annee;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "resume", length = 510)
    private String resume;


    @ManyToOne
    @JoinColumn(name = "lieu_id", referencedColumnName = "lieu_id")
    private Lieu lieuTournage;
    @ManyToOne
    @JoinColumn(name = "pays_id", referencedColumnName = "pays_id")
    private Pays pays;

    @OneToMany(mappedBy = "film")
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "Films_Langues",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "langue_id", referencedColumnName = "langue_id"))
    private List<Langue> langues = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "Films_Genres",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "genre_id"))
    private List<Genre> genres = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "Films_Realisateurs",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "realisateur_id", referencedColumnName = "personne_id"))
    private List<Realisateur> realisateurs = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "CastingPrincipal",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id", referencedColumnName = "personne_id"))
    private List<Acteur> acteurs = new ArrayList<>();


    public Film() {
    }

    /**
     * Gets filmId for the class Film
     *
     * @return value of filmId
     */
    public Long getFilmId() {
        return filmId;
    }

    /**
     * Gets imdbId for the class Film
     *
     * @return value of imdbId
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * Sets imdbId for the class Film.
     *
     * @param imdbId value of imdbId
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * Gets titre for the class Film
     *
     * @return value of titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Sets titre for the class Film.
     *
     * @param titre value of titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Gets annee for the class Film
     *
     * @return value of annee
     */
    public Year getAnnee() {
        return annee;
    }

    /**
     * Sets annee for the class Film.
     *
     * @param annee value of annee
     */
    public void setAnnee(Year annee) {
        this.annee = annee;
    }

    /**
     * Gets rating for the class Film
     *
     * @return value of rating
     */
    public Float getRating() {
        return rating;
    }

    /**
     * Sets rating for the class Film.
     *
     * @param rating value of rating
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }

    /**
     * Gets resume for the class Film
     *
     * @return value of resume
     */
    public String getResume() {
        return resume;
    }

    /**
     * Sets resume for the class Film.
     *
     * @param resume value of resume
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * Gets lieuTournage for the class Film
     *
     * @return value of lieuTournage
     */
    public Lieu getLieuTournage() {
        return lieuTournage;
    }

    /**
     * Sets lieuTournage for the class Film.
     *
     * @param lieuTournage value of lieuTournage
     */
    public void setLieuTournage(Lieu lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    /**
     * Gets pays for the class Film
     *
     * @return value of pays
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Sets pays for the class Film.
     *
     * @param pays value of pays
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * Gets roles for the class Film
     *
     * @return value of roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles for the class Film.
     *
     * @param roles value of roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Gets langues for the class Film
     *
     * @return value of langues
     */
    public List<Langue> getLangues() {
        return langues;
    }

    /**
     * Sets langues for the class Film.
     *
     * @param langues value of langues
     */
    public void setLangues(List<Langue> langues) {
        this.langues = langues;
    }

    /**
     * Gets genres for the class Film
     *
     * @return value of genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Sets genres for the class Film.
     *
     * @param genres value of genres
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Gets realisateurs for the class Film
     *
     * @return value of realisateurs
     */
    public List<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    /**
     * Sets realisateurs for the class Film.
     *
     * @param realisateurs value of realisateurs
     */
    public void setRealisateurs(List<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    /**
     * Gets acteurs for the class Film
     *
     * @return value of acteurs
     */
    public List<Acteur> getActeurs() {
        return acteurs;
    }

    /**
     * Sets acteurs for the class Film.
     *
     * @param acteurs value of acteurs
     */
    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(filmId, film.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("filmId=").append(filmId);
        sb.append(", imdbId='").append(imdbId).append('\'');
        sb.append(", titre='").append(titre).append('\'');
        sb.append(", annee=").append(annee);
        sb.append(", rating=").append(rating);
        sb.append(", resume='").append(resume).append('\'');
        sb.append(", lieuTournage=").append(lieuTournage);
        sb.append(", pays=").append(pays);
        sb.append(", roles=").append(roles);
        sb.append(", langues=").append(langues);
        sb.append(", genres=").append(genres);
        sb.append(", realisateurs=").append(realisateurs);
        sb.append(", acteurs=").append(acteurs);
        sb.append('}');
        return sb.toString();
    }
}