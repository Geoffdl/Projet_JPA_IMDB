package fr.diginamic.geoff.dto;

/**
 * @author Geoff
 * <p>
 * Data Transfer Object for JSON to POJO conversion of lieux de tournage
 */
public class LieuTournageDTO implements NaturalIdentifiable {
    private String ville;
    private String etatDept;
    private String pays;

    public LieuTournageDTO() {
    }

    /**
     * Gets ville for the class LieuTournageDTO
     *
     * @return value of ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Sets ville for the class LieuTournageDTO.
     *
     * @param ville value of ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Gets etatDept for the class LieuTournageDTO
     *
     * @return value of etatDept
     */
    public String getEtatDept() {
        return etatDept;
    }

    /**
     * Sets etatDept for the class LieuTournageDTO.
     *
     * @param etatDept value of etatDept
     */
    public void setEtatDept(String etatDept) {
        this.etatDept = etatDept;
    }

    /**
     * Gets pays for the class LieuTournageDTO
     *
     * @return value of pays
     */
    public String getPays() {
        return pays;
    }

    /**
     * Sets pays for the class LieuTournageDTO.
     *
     * @param pays value of pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LieuTournageDTO{");
        sb.append("ville='").append(ville).append('\'');
        sb.append(", etatDept='").append(etatDept).append('\'');
        sb.append(", pays='").append(pays).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getNaturalId() {
        StringBuilder sb = new StringBuilder().append(ville).append(",").append(etatDept);
        return sb.toString();
    }
}