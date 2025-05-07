package fr.diginamic.geoff.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Realisateurs")
public class Realisateur extends Personne
{

    @ManyToMany(mappedBy = "realisateurs")
    List<Film> films = new ArrayList<>();



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