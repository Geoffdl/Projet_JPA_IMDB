package fr.diginamic.geoff.utils;

public class PaysUtils {


    public static String extractPaysFromLieuNaissance(String lieuNaissance) {
        if (lieuNaissance == null) {
            return null;
        }
        String[] parts = lieuNaissance.split(",");

        return parts[parts.length -1];
    }


    /**
     * Cleans up a messy country string and extracts the most likely country name.
     * @param rawCountry the raw country string
     * @return cleaned country name, or null if input is null/blank
     */
    public static String cleanCountryName(String rawCountry) {
        if (rawCountry == null || rawCountry.isBlank()) return null;

        String cleaned = rawCountry;

        // Handle "[now Country]" pattern
        if (cleaned.contains("[now")) {
            int start = cleaned.indexOf("[now") + 4;
            int end = cleaned.indexOf("]", start);
            if (end > start) {
                cleaned = cleaned.substring(start, end).trim();
                return cleaned;
            }
        }

        // Remove anything in brackets or parentheses
        cleaned = cleaned.replaceAll("\\[.*?\\]", "");
        cleaned = cleaned.replaceAll("\\(.*?\\)", "");

        // Remove stray trailing or leading non-letter characters (like ']')
        cleaned = cleaned.replaceAll("^[^\\p{L}]+|[^\\p{L}]+$", "");


        if(cleaned.trim().isEmpty()){
            return null;
        }
        return cleaned.trim();
    }

}