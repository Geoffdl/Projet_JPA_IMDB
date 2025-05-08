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
@Table(name = "Genres")
public class Genre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", length = 11)
    private Long genreId;

    @Column(name = "nom", length = 50)
    private String nom;

    @ManyToMany(mappedBy = "genres")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Film> films = new ArrayList<>();


    public Genre()
    {
    }

    /**
     * Gets genreId for the class Genre
     *
     * @return value of genreId
     */
    public Long getGenreId() {
        return genreId;
    }


    /**
     * Gets nom for the class Genre
     *
     * @return value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom for the class Genre.
     *
     * @param nom value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets films for the class Genre
     *
     * @return value of films
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Sets films for the class Genre.
     *
     * @param films value of films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Genre genre)) return false;
        return Objects.equals(nom, genre.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }
}