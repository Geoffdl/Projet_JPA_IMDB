package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Acteurs")
public class Acteur extends Personne
{
    @Column(name = "taille")
    private Float taille;


    @ManyToMany(mappedBy = "acteurs")
   private List<Film> films = new ArrayList<>();

    @OneToMany(mappedBy = "acteur")
    private List<Role> roles =new ArrayList<>();

    public Acteur()
    {
    }

    /**
     * Gets taille for the class Acteur
     *
     * @return value of taille
     */
    public Float getTaille() {
        return taille;
    }

    /**
     * Sets taille for the class Acteur.
     *
     * @param taille value of taille
     */
    public void setTaille(Float taille) {
        this.taille = taille;
    }

    /**
     * Gets films for the class Acteur
     *
     * @return value of films
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Sets films for the class Acteur.
     *
     * @param films value of films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    /**
     * Gets roles for the class Acteur
     *
     * @return value of roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles for the class Acteur.
     *
     * @param roles value of roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


}