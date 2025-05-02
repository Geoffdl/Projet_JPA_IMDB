package fr.diginamic.geoff.mapper;

public interface EntityMapper<D, E> {

    E mapToEntity(D dto);
}