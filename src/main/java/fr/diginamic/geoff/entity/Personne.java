package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Personnes")
public abstract class Personne
{
    @Id
    @Column(name = "personne_id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personneId;

    @Column(name = "id_imdb", length = 9)
    private String imdbId;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "dateNaissance")
    private LocalDate dateNaissance;

    @Column(name = "url", length = 255)
    private String url;

    @OneToOne
    @JoinColumn(name = "lieu_id")
    private Lieu lieuxNaissance;


    public Personne()
    {
    }

    /**
     * Gets personneId for the class Personne
     *
     * @return value of personneId
     */
    public Long getPersonneId() {
        return personneId;
    }

    /**
     * Gets imdbId for the class Personne
     *
     * @return value of imdbId
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * Sets imdbId for the class Personne.
     *
     * @param imdbId value of imdbId
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * Gets nom for the class Personne
     *
     * @return value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom for the class Personne.
     *
     * @param nom value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets prenom for the class Personne
     *
     * @return value of prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets prenom for the class Personne.
     *
     * @param prenom value of prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Gets dateNaissance for the class Personne
     *
     * @return value of dateNaissance
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Sets dateNaissance for the class Personne.
     *
     * @param dateNaissance value of dateNaissance
     */
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Gets url for the class Personne
     *
     * @return value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url for the class Personne.
     *
     * @param url value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets lieuxNaissance for the class Personne
     *
     * @return value of lieuxNaissance
     */
    public Lieu getLieuxNaissance() {
        return lieuxNaissance;
    }

    /**
     * Sets lieuxNaissance for the class Personne.
     *
     * @param lieuxNaissance value of lieuxNaissance
     */
    public void setLieuxNaissance(Lieu lieuxNaissance) {
        this.lieuxNaissance = lieuxNaissance;
    }
}