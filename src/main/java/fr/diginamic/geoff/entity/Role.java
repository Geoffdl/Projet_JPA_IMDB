package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Role
{
    @EmbeddedId
    private RoleId id;

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

    public RoleId getId()
    {
        return id;
    }

    public void setId(RoleId id)
    {
        this.id = id;
    }
}

@Embeddable
class RoleId
{
    @Column(name = "Id_Acteur")
    private String idActeur;
    @Column(name = "Id_Film")
    private String idFilm;

    public RoleId()
    {
    }

    public RoleId(String idActeur, String idFilm)
    {
        this.idActeur = idActeur;
        this.idFilm = idFilm;
    }

    public String getIdActeur()
    {
        return idActeur;
    }

    public void setIdActeur(String idActeur)
    {
        this.idActeur = idActeur;
    }

    public String getIdFilm()
    {
        return idFilm;
    }

    public void setIdFilm(String idFilm)
    {
        this.idFilm = idFilm;
    }
}