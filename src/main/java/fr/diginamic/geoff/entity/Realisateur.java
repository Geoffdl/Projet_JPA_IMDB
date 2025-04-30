package fr.diginamic.geoff.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Realisateurs")
public class Realisateur extends Personne
{

    @ManyToMany
    @JoinTable(name = "Films_Realisateurs",
            joinColumns = @JoinColumn(name = "Id_Realisateur", referencedColumnName = "Id_Personne"),
            inverseJoinColumns = @JoinColumn(name = "Id_Film", referencedColumnName = "Id_Film"))
    List<Film> films;


    public Realisateur()
    {
    }

    public List<Film> getFilms()
    {
        return films;
    }

    public void setFilms(List<Film> films)
    {
        this.films = films;
    }
}