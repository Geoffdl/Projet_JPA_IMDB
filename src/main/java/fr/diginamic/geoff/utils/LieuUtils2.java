package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.NaissanceDTO;

public class LieuUtils2 {

    /**
     * Extract the city/town part from a birth location
     * For locations like "City, Region, Country", returns "City"
     * For locations like "City-Neighborhood, Region, Country", returns "City-Neighborhood"
     */
    public static String extractLieuVille(NaissanceDTO dto) {
        String lieu = dto.getLieuNaissance();
        if (lieu == null || lieu.trim().isEmpty()) {
            return "";
        }

        String[] parts = lieu.split(",");
        if (parts.length == 0) {
            return "";
        }

        // First part is always the city/town
        return parts[0].trim();
    }

    /**
     * Extract the region/state part from a birth location
     * For locations like "City, Region, Country", returns "Region"
     * For locations like "City, Country", returns ""
     */
    public static String extractLieuRegion(NaissanceDTO dto) {
        String lieu = dto.getLieuNaissance();
        if (lieu == null || lieu.trim().isEmpty()) {
            return "";
        }

        String[] parts = lieu.split(",");
        if (parts.length < 2) {
            return "";  // No region info available
        } else if (parts.length == 2) {
            // For "City, Country" format, there's no region
            return "";
        } else {
            // For "City, Region, Country" format, region is the second-to-last part
            return parts[parts.length - 2].trim();
        }
    }

    /**
     * Extract the full location string for display purposes
     */
    public static String extractLibelle(NaissanceDTO dto) {
        String lieu = dto.getLieuNaissance();
        if (lieu == null) {
            return "";
        }
        return lieu.trim();
    }

    /**
     * Determines whether the location has enough information to create a valid Lieu entity
     */
    public static boolean isValidLieu(NaissanceDTO dto) {
        String lieu = dto.getLieuNaissance();
        return lieu != null && !lieu.trim().isEmpty() && lieu.contains(",");
    }
}