package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.FilmDTO;
import fr.diginamic.geoff.dto.NaissanceDTO;

import java.util.List;
import java.util.stream.Stream;

public class NaissanceUtils {
    public static List<NaissanceDTO> collectAllNaissancesAsList(List<FilmDTO> filmDTOList) {


        List<NaissanceDTO> naissanceFromCasting = filmDTOList.stream()
                .filter(f -> f.getCastingPrincipal() != null)
                .flatMap(f -> f.getCastingPrincipal().stream()
                        .filter(a -> a != null && a.getNaissance() != null)
                        .map(c -> c.getNaissance()))
                .toList();

        List<NaissanceDTO> naissanceFromRoles = filmDTOList.stream()
                .filter(f -> f.getRoles() != null)
                .flatMap(f -> f.getRoles().stream()
                        .filter(r -> r != null && r.getActeur() != null && r.getActeur().getNaissance() != null)
                        .map(r -> r.getActeur().getNaissance()))
                .toList();

        List<NaissanceDTO> naissanceFromRealisateurs = filmDTOList.stream()
                .filter(f -> f.getRealisateurs() != null)
                .flatMap(f -> f.getRealisateurs().stream()
                        .filter(r -> r != null && r.getNaissance() != null)
                        .map(r -> r.getNaissance()))
                .toList();

        List<NaissanceDTO> resultList = Stream.of(naissanceFromCasting, naissanceFromRoles, naissanceFromRealisateurs)
                .flatMap(l -> l.stream())
                .toList();

        return resultList;
    }
}