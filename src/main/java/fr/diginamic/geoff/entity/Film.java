package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Films")
public class Film
{
    @Id
    @Column(name = "Id_Film")
    private String idImdb;

    @Column(name = "Titre")
    private String titre;
    @Column(name = "Annee")
    private Year annee;
    @Column(name = "Rating")
    private Float rating;
    @Column(name = "Langue")
    private String langue;
    @Column(name = "Resume")
    private String resume;

    @ManyToOne
    @JoinColumn(name = "Id_Lieu_Tournage", referencedColumnName = "Id_Lieu")
    private Lieu lieuTournage;

    @ManyToOne
    @JoinColumn(name = "Id_Pays", referencedColumnName = "Id_Pays")
    private Pays pays;

    @OneToMany(mappedBy = "film")
    private List<Role> roles;

    @ManyToMany(mappedBy = "films")
    private List<Genre> genres = new ArrayList<>();
    @ManyToMany(mappedBy = "films")
    private List<Realisateur> realisateurs = new ArrayList<>();
    @ManyToMany(mappedBy = "films")
    private List<Acteur> acteurs = new ArrayList<>();


    public Film()
    {
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public List<Genre> getGenres()
    {
        return genres;
    }

    public void setGenres(List<Genre> genres)
    {
        this.genres = genres;
    }

    public List<Realisateur> getRealisateurs()
    {
        return realisateurs;
    }

    public void setRealisateurs(List<Realisateur> realisateurs)
    {
        this.realisateurs = realisateurs;
    }

    public List<Acteur> getActeurs()
    {
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs)
    {
        this.acteurs = acteurs;
    }



    public String getIdImdb()
    {
        return idImdb;
    }

    public void setIdImdb(String idImdb)
    {
        this.idImdb = idImdb;
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public Year getAnnee()
    {
        return annee;
    }

    public void setAnnee(Year annee)
    {
        this.annee = annee;
    }

    public Float getRating()
    {
        return rating;
    }

    public void setRating(Float rating)
    {
        this.rating = rating;
    }

    public String getLangue()
    {
        return langue;
    }

    public void setLangue(String langue)
    {
        this.langue = langue;
    }

    public String getResume()
    {
        return resume;
    }

    public void setResume(String resume)
    {
        this.resume = resume;
    }

    public Lieu getLieuTournage()
    {
        return lieuTournage;
    }

    public void setLieuTournage(Lieu lieuTournage)
    {
        this.lieuTournage = lieuTournage;
    }

    public Pays getPays()
    {
        return pays;
    }

    public void setPays(Pays pays)
    {
        this.pays = pays;
    }
}