package fr.diginamic.geoff.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Pays", uniqueConstraints = @UniqueConstraint(columnNames = "nom"))
public class Pays
{
    @Id
    @Column(name = "pays_id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paysId;

    @Column(name = "nom", length = 100, nullable = false)
    private String nom;

    @Column(name = "url", length = 255)
    private String url;

    @OneToMany(mappedBy = "pays")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Film> films = new ArrayList<>();

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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pays pays)) return false;
        return Objects.equals(nom, pays.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }
}