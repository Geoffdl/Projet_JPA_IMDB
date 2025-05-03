package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.ActeurDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ActeurUtils implements EntityUtils<ActeurDTO>{

    /**
     * Uses acteur imdb as a natural ID to create a map of filmImdbId, filmObject and filter out duplicates by keeping only the first occurrence
     * @param acteurDTOList list of raw acteurDTOs
     * @return List of acteurs without duplicates
     */
    public static List<ActeurDTO> removeDuplicatesByImdbId(List<ActeurDTO> acteurDTOList) {


        Map<String, ActeurDTO> acteurDTOMap = acteurDTOList.stream().collect(Collectors.toMap(ActeurDTO::getId, Function.identity(), (a, b) -> a));

        return new ArrayList<>(acteurDTOMap.values());

    }


}