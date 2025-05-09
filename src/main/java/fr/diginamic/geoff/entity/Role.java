package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", length = 11)
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "acteur_id", referencedColumnName = "personne_id")
    private Acteur acteur;

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;

    @Column(name = "personnage", length = 100)
    private String personnage;


    public Role()
    {
    }

    /**
     * Gets roleId for the class Role
     *
     * @return value of roleId
     */
    public Long getRoleId() {
        return roleId;
    }


    /**
     * Gets acteur for the class Role
     *
     * @return value of acteur
     */
    public Acteur getActeur() {
        return acteur;
    }

    /**
     * Sets acteur for the class Role.
     *
     * @param acteur value of acteur
     */
    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    /**
     * Gets film for the class Role
     *
     * @return value of film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Sets film for the class Role.
     *
     * @param film value of film
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Gets personnage for the class Role
     *
     * @return value of personnage
     */
    public String getPersonnage() {
        return personnage;
    }

    /**
     * Sets personnage for the class Role.
     *
     * @param personnage value of personnage
     */
    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Role role)) return false;
        return Objects.equals(personnage, role.personnage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(personnage);
    }
}