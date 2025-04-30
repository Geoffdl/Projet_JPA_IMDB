package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Acteurs")
public class Acteur extends Personne
{
    @Column(name = "Taille")
    private String taille;


    @ManyToMany
    @JoinTable(name = "CastingPrincipal",
            joinColumns = @JoinColumn(name = "Id_Acteur", referencedColumnName = "Id_Personne"),
            inverseJoinColumns = @JoinColumn(name = "Id_Film", referencedColumnName = "Id_Film"))
    List<Film> films;

    @OneToMany(mappedBy = "acteur")
    private List<Role> roles;

    public Acteur()
    {
    }

    public String getTaille()
    {
        return taille;
    }

    public void setTaille(String taille)
    {
        this.taille = taille;
    }

    public List<Film> getFilms()
    {
        return films;
    }

    public void setFilms(List<Film> films)
    {
        this.films = films;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }
}