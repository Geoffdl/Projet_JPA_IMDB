package fr.diginamic.geoff.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Realisateurs")
public class Realisateur extends Personne
{

    @ManyToMany
    @JoinTable(name = "Films_Realisateurs",
            joinColumns = @JoinColumn(name = "realisateur_id", referencedColumnName = "personne_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    List<Film> films;



    public Realisateur()
    {
    }

    /**
     * Gets films for the class Realisateur
     *
     * @return value of films
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Sets films for the class Realisateur.
     *
     * @param films value of films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }
}