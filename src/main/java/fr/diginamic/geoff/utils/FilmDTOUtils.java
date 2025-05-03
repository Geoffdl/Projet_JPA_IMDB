package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.FilmDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FilmDTOUtils {

    /**
     * Uses film title as a natural ID to create a map of filmTitle, filmObject and filter out duplicates by keeping only the first occurrence
     * @param films list of raw filmDTOs
     * @return List of films without duplicates
     */
    public static List<FilmDTO> removeDuplicateByTitle(List<FilmDTO> films) {

        Map<String, FilmDTO> filmMap = films.stream().collect(Collectors.toMap(FilmDTO::getNom, Function.identity(), (a, b) -> a));
        films = new ArrayList<>(filmMap.values());
        return films;

    }

}