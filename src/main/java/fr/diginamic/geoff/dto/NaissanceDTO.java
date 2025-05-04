package fr.diginamic.geoff.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.diginamic.geoff.dto.deserializer.LocalDateDeserializer;

import java.time.LocalDate;

/**
 * @author Geoff
 * <p>
 * Data Transfer Object for JSON to POJO conversion of naissances
 */
public class NaissanceDTO {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateNaissance;

    private String lieuNaissance;

    public NaissanceDTO() {
    }

    /**
     * Gets dateNaissance for the class NaissanceDTO
     *
     * @return value of dateNaissance
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Sets dateNaissance for the class NaissanceDTO.
     *
     * @param dateNaissance value of dateNaissance
     */
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Gets lieuNaissance for the class NaissanceDTO
     *
     * @return value of lieuNaissance
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Sets lieuNaissance for the class NaissanceDTO.
     *
     * @param lieuNaissance value of lieuNaissance
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NaissanceDTO{");
        sb.append("dateNaissance=").append(dateNaissance);
        sb.append(", lieuNaissance='").append(lieuNaissance).append('\'');
        sb.append('}');
        return sb.toString();
    }
}