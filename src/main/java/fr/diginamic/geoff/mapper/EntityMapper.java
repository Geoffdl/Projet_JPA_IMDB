package fr.diginamic.geoff.mapper;

/**
 * Interface containing methods handling DTO and Entity conversions
 * @param <D> the source class
 * @param <E> the target class
 */
public interface EntityMapper<D, E> {

    /**
     * Method that takes in a DTO an converts it into an Entity
     * @param dto takes a DTO object as parameter
     * @return returns an Entity object with non-relationship based attributes set
     *
     */
    E mapToEntity(D dto) ;
}