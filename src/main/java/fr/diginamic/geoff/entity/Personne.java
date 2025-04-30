package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Personnes")
public abstract class Personne
{
    @Id
    @Column(name = "Id_Personne")
    private String id_Imdb;

    @Column(name = "Nom")
    private String nom;

    @Column(name = "Prenom")
    private String prenom;

    @Column(name = "Date_Naissance")
    private LocalDate dateNaissance;
    @Column(name = "Url")
    private String url;

    @OneToOne
    @JoinColumn(name = "Id_Lieux_Naissance")
    private Lieu lieuxNaissance;


    public Personne()
    {
    }

    public String getId_Imdb()
    {
        return id_Imdb;
    }

    public void setId_Imdb(String id_Imdb)
    {
        this.id_Imdb = id_Imdb;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance()
    {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance)
    {
        this.dateNaissance = dateNaissance;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Lieu getLieuxNaissance()
    {
        return lieuxNaissance;
    }

    public void setLieuxNaissance(Lieu lieuxNaissance)
    {
        this.lieuxNaissance = lieuxNaissance;
    }
}