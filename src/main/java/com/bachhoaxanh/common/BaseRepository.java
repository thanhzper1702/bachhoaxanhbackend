package com.bachhoaxanh.common;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface defining standard CRUD operations
 * for Jakarta Persistence data access layers.
 *
 * @param <T>  Entity type
 * @param <ID> Entity identifier type
 */
public interface BaseRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAllActive();
    void deleteById(ID id);
    void softDeleteById(ID id);
    boolean existsById(ID id);
}
