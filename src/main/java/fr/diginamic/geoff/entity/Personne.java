package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Personnes",
        indexes = {@Index(name = "index_personne_imdbid", columnList = "id_imdb")},
        uniqueConstraints = @UniqueConstraint(name = "uc_id_imdb", columnNames = "id_imdb"))
public abstract class Personne {
    @Id
    @Column(name = "personne_id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personneId;

    @Column(name = "id_imdb", length = 12)
    private String imdbId;

    @Column(name = "identite", length = 100)
    private String identite;

    @Column(name = "dateNaissance")
    private LocalDate dateNaissance;

    @Column(name = "url", length = 255)
    private String url;

    @ManyToOne
    @JoinColumn(name = "lieu_id")
    private Lieu lieuxNaissance;

    public Personne() {
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

    /**
     * Gets identite for the class Personne
     *
     * @return value of identite
     */
    public String getIdentite() {
        return identite;
    }

    /**
     * Sets identite for the class Personne.
     *
     * @param identite value of identite
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Personne personne))
            return false;
        return Objects.equals(imdbId, personne.imdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(imdbId);
    }
}