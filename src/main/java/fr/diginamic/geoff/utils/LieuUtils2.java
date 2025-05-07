package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.NaissanceDTO;

public class LieuUtils2 {

    /**
     * @param dto
     * @return
     */
    public static String extractLieuVille(NaissanceDTO dto) {
        if (dto == null || dto.getLieuNaissance() == null) {
            return "";
        }
        String[] parts = dto.getLieuNaissance().split(",");

        switch (parts.length) {
            case 1:
                return parts[0].trim();
            case 2:
                return parts[0].trim();

            case 3:
                return parts[0].trim();
            case 4:
                StringBuilder sb = new StringBuilder();
                return sb.append(parts[0].trim()).append(",").append(parts[1].trim()).toString();
            default:
                return parts[0].trim();
        }

    }

    /**
     *
     * @param dto
     * @return
     */
    public static String extractLieuRegion(NaissanceDTO dto) {
        if (dto == null || dto.getLieuNaissance() == null) {
            return "";
        }
        String[] parts = dto.getLieuNaissance().split(",");
        switch (parts.length) {
            case 1:
                return "";
            case 2:
                return "";

            case 3:
                return parts[1].trim();
            case 4:
                return parts[2].trim();
            default:
                return parts[parts.length - 2].trim();
        }

    }

    /**
     *
     * @param dto
     * @return
     */
    public static String extractLibelle(NaissanceDTO dto) {
        if (dto == null || dto.getLieuNaissance() == null) {
            return "";
        }
        String libbelle = dto.getLieuNaissance();
        String[] parts = libbelle.split(",");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length -1; i++) {
            sb.append(parts[i].trim());
            if (i < parts.length - 2) {
                sb.append(",");
            }
        }

        return sb.toString();

    }
}