package fr.diginamic.geoff.dao;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T>{

    Optional<T> findById(long id);

    Optional<T> findOrCreate(T entity);

    List<T> findAll();
    void save();
    void delete(T entity);


}