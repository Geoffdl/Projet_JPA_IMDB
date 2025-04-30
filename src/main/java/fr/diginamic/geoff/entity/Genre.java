package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Genres")
public class Genre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Genre")
    private Long id;

    @Column(name = "Nom")
    private String nom;

    @ManyToMany
    @JoinTable(name = "Films_Genres",
            inverseJoinColumns = @JoinColumn(name = "Id_Film", referencedColumnName = "Id_Film"),
            joinColumns = @JoinColumn(name = "Id_Genre", referencedColumnName = "Id_Genre"))
    private List<Film> films = new ArrayList<>();


    public Genre()
    {
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
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