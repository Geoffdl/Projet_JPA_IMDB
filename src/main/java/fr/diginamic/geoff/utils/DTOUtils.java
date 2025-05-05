package fr.diginamic.geoff.utils;

import fr.diginamic.geoff.dto.NaturalIdentifiable;
import fr.diginamic.geoff.dto.PaysDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DTOUtils {

    /**
     * This method applies to all DTO objects that implement the interface NaturalIdentifiable to determine a unique identifier.
     * It creates a Hashmap of : NatID + DTOObject and in case of duplicates it keeps the first instance
     *
     * @param listOfDto list of raw filmDTOs
     * @return ListOfDto without duplicates by NatId
     */
    public static <T extends NaturalIdentifiable> List<T> removeDuplicatesByNaturalId(List<T> listOfDto) {

        Map<String, T> map = listOfDto.stream()
                .collect(Collectors.toMap(
                        NaturalIdentifiable::getNaturalId, //The implementation of NaturalIdentifiable
                        Function.identity(), //The class of the source list
                        (existing, replacement) -> existing)); //keep first instance

        return new ArrayList<>(map.values());
    }

    public static String extractPaysFromLieuNaissance(String lieuNaissance) {
        if (lieuNaissance == null) {
            return null;
        }
        String[] parts = lieuNaissance.split(",");

        return parts[parts.length - 1].trim();
    }

    public static PaysDTO extractPaysFromCountryName(String countryName) {
        if (countryName == null) {
            return null;
        }
        PaysDTO paysDTO = new PaysDTO();
        paysDTO.setNom(countryName);
        return paysDTO;
    }
}