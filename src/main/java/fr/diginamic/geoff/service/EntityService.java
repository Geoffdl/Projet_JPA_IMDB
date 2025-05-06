package fr.diginamic.geoff.service;

import fr.diginamic.geoff.dto.FilmDTO;

import java.util.List;

public interface EntityService<T, D> {


    T createEntity(FilmDTO filmDTO);
    /**
     * This method handles the conversion of DTO to Entity.
     * @param filmDTOList list of raw DTO
     * @return listOf Realisateurs
     */
    List<T> createEntityList(List<FilmDTO> filmDTOList);

    /**
     * This methods parses through the DTO list and removes duplicates
     * @param filmDTOList data source
     * @return list of specific DTO before entity conversion
     */
     List<D> getList(List<FilmDTO> filmDTOList);


}