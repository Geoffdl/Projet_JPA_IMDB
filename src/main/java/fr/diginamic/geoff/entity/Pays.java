package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pays")
public class Pays
{
    @Id
    @Column(name = "pays_id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paysId;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "url", length = 255)
    private String url;

    @OneToMany
    @JoinColumn(name = "film_id")
    private List<Film> films;

    public Pays()
    {
    }

    /**
     * Gets paysId for the class Pays
     *
     * @return value of paysId
     */
    public Long getPaysId() {
        return paysId;
    }

    /**
     * Gets nom for the class Pays
     *
     * @return value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom for the class Pays.
     *
     * @param nom value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets url for the class Pays
     *
     * @return value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url for the class Pays.
     *
     * @param url value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets films for the class Pays
     *
     * @return value of films
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Sets films for the class Pays.
     *
     * @param films value of films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }
}