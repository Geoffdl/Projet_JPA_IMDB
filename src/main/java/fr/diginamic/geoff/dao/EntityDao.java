package fr.diginamic.geoff.dao;

import fr.diginamic.geoff.exception.DuplicateEntityException;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T>{

    Optional<T> findById(long id);

    Optional<T> findOrCreate(T entity) throws DuplicateEntityException;

    List<T> findAll();

    void delete(T entity);


}