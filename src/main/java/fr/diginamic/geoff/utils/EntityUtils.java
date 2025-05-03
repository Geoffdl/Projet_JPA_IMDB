package fr.diginamic.geoff.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface EntityUtils<T> {
    /**
     * Uses a natural ID to create a map of entityDTO.naturalID, entityDTO object and filter out duplicates by keeping only the first occurrence
     *
     * @param entities list of raw entityDTO objects
     * @return List of entityDTOobjects without duplicates
     */
    default List<T> removeDuplicatesByNaturalId(List<T> entities, Function<T, String> keyExtractor) {
        return new ArrayList<>(entities.stream().collect(Collectors.toMap(keyExtractor, Function.identity(), (a, b) -> a)).values());
    }

}