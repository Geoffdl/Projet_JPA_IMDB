package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Lieux")
public class Lieu
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Lieu")
    private Long id;

    @Column(name = "Region")
    private String region;
    @Column(name = "Ville")
    private String ville;
    @Column(name = "Libelle")
    private String libelle;

    @OneToOne
    @JoinColumn(name = "Id_Pays")
    private Pays pays;

    public Lieu()
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

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public String getLibelle()
    {
        return libelle;
    }

    public void setLibelle(String libelle)
    {
        this.libelle = libelle;
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