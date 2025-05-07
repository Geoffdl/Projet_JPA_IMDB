package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.NaissanceDTO;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LieuUtils {

    public static String extractLieuVille(NaissanceDTO dto) {
        String lieu = dto.getLieuNaissance();
        if (lieu == null) {
            return "";
        }
        String[] parts = lieu.split(",");
        if (parts.length <= 2) {
            return parts[0].trim();
        }
        return Arrays.stream(parts, 0, parts.length - 2)
                .map(String::trim)
                .collect(Collectors.joining(", "));
    }

    public static String extractLieuRegion(NaissanceDTO dto) {
        String lieu = dto.getLieuNaissance();
        if (lieu == null) {
            return "";
        }

        String[] parts = lieu.split(",");
        if (parts.length < 2) {
            return "";
        }
        return parts[parts.length - 2].trim();
    }

    public static String extractLibelle(NaissanceDTO dto) {
        String lieu = dto.getLieuNaissance();
        if (lieu == null) {
            return "";
        }
        String[] parts = lieu.split(",");
        return parts[parts.length - 1].trim();
    }
}