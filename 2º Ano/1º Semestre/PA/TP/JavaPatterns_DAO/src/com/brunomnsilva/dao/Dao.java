package com.brunomnsilva.dao;

import java.util.Collection;

/**
 * An abstract API that performs CRUD operations on objects of type T and unique key K
 *
 * @param <T> type of transfer object
 * @param <K> unique key for instance/record
 *
 * @author brunomnsilva
 */
public interface Dao<T, K> {

    /**
     * Returns the transfer object for the key <code>key</code>.
     * @param key data key
     * @return transfer object
     */
    T get(K key);

    /**
     * Returns a collection of all transfer objects currently in the underlying repo.
     * @return collection of transfer objects
     */
    Collection<T> getAll();

    /**
     * Save the transfer object.
     * @param instance instance of transfer object to save.
     * @throws DaoException if the key for this transfer object already exists
     */
    void save(T instance) throws DaoException;

    /**
     * Updates the transfer object.
     * @param instance instance of transfer object to update.
     * @throws DaoException if the key for this transfer object does not exist
     */
    void update(T instance) throws DaoException;

    /**
     * Deletes the instance/record for the <code>key</code>.
     * @param key key of the unserlying instance/record
     * @return a transfer object with the removed data; or <b>null</b> if the key does not exist.
     */
    T delete(K key);

    /**
     * Returns the number of instances/records in the repo.
     * @return instances/records count.
     */
    int count();
}
