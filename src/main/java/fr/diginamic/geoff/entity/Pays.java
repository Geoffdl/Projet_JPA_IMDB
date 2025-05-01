package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pays")
public class Pays
{
    @Id
    @Column(name = "Id_Pays", length = 50)
    private String nom;

    @Column(name = "URL", length = 255)
    private String url;

    @OneToMany
    @JoinColumn(name = "Id_Film")
    private List<Film> films;

    public Pays()
    {
    }


    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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