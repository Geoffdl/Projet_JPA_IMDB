package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Roles")
@IdClass(RoleId.class)
public class Role
{

    @Id
    @Column(name = "Id_Acteur")
    private String idActeur;

    @Id
    @Column(name = "Id_Film")
    private String idFilm;


    @ManyToOne
    @JoinColumn(name = "Id_Acteur", referencedColumnName = "Id_Personne")
    private Acteur acteur;

    @ManyToOne
    @JoinColumn(name = "Id_Film", referencedColumnName = "Id_Film")
    private Film film;

    @Column(name = "Personnage")
    private String personnage;


    public Role()
    {
    }

    public Acteur getActeur()
    {
        return acteur;
    }

    public void setActeur(Acteur acteur)
    {
        this.acteur = acteur;
    }

    public Film getFilm()
    {
        return film;
    }

    public void setFilm(Film film)
    {
        this.film = film;
    }

    public String getPersonnage()
    {
        return personnage;
    }

    public void setPersonnage(String personnage)
    {
        this.personnage = personnage;
    }
}

class RoleId
{
    private String idActeur;
    private String idFilm;

}