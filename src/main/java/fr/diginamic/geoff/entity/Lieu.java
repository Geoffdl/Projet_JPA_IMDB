package fr.diginamic.geoff.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Lieux")
public class Lieu
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lieu_id", length = 11)
    private Long lieuId;

    @Column(name = "region", length = 50)
    private String region;
    @Column(name = "ville", length = 50)
    private String ville;
    @Column(name = "libelle", length = 255)
    private String libelle;

    @OneToOne
    @JoinColumn(name = "pays_id")
    private Pays pays;

    public Lieu()
    {
    }

    /**
     * Gets lieuId for the class Lieu
     *
     * @return value of lieuId
     */
    public Long getLieuId() {
        return lieuId;
    }

    /**
     * Sets lieuId for the class Lieu.
     *
     * @param lieuId value of lieuId
     */
    public void setLieuId(Long lieuId) {
        this.lieuId = lieuId;
    }

    /**
     * Gets region for the class Lieu
     *
     * @return value of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets region for the class Lieu.
     *
     * @param region value of region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets ville for the class Lieu
     *
     * @return value of ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Sets ville for the class Lieu.
     *
     * @param ville value of ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Gets libelle for the class Lieu
     *
     * @return value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Sets libelle for the class Lieu.
     *
     * @param libelle value of libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Gets pays for the class Lieu
     *
     * @return value of pays
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Sets pays for the class Lieu.
     *
     * @param pays value of pays
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }
}