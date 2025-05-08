package fr.diginamic.geoff.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "langues")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "langue_id", length = 11)
    private Long langueId;

    @Column(name = "nom", length = 50)
    private String nom;

    @ManyToMany(mappedBy = "langues")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Film> films = new ArrayList<>();

    public Langue() {
    }

    /**
     * Gets langueId for the class Langue
     *
     * @return value of langueId
     */
    public Long getLangueId() {
        return langueId;
    }


    /**
     * Gets nom for the class Langue
     *
     * @return value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom for the class Langue.
     *
     * @param nom value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets films for the class Langue
     *
     * @return value of films
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Sets films for the class Langue.
     *
     * @param films value of films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Langue langue)) return false;
        return Objects.equals(nom, langue.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }
}