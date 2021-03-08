package pt.pa.adts;

import java.util.Collection;

/**
 * An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface Map<K, V> {

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced by the specified value.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key, if the implementation supports null values.)
     * @throws NullPointerException if the specified key or value is null and this map does not permit null keys or values
     */
    V put(K key, V value) throws NullPointerException;

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * If this map permits null values, then a return value of null does not necessarily indicate that the map contains
     * no mapping for the key; it's also possible that the map explicitly maps the key to null.
     * The {@link #containsKey(Object)} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null and this map does not permit null keys (optional)
     */
    V get(K key) throws NullPointerException;

    /**
     * Removes the mapping for a key from this map if it is present.
     * Returns the value to which this map previously associated the key, or null if the map contained no mapping for the key.
     *
     * If this map permits null values, then a return value of null does not necessarily indicate that the map contained no mapping for the key; it's also possible that the map explicitly mapped the key to null.
     *
     * The map will not contain a mapping for the specified key once the call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     * @throws NullPointerException if the specified key is null and this map does not permit null keys (optional)
     */
    V remove(K key) throws NullPointerException;

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     * @throws NullPointerException if the specified key is null and this map does not permit null keys (optional)
     */
    boolean containsKey(K key) throws NullPointerException;

    /**
     * Returns a collection view of the keys contained in this map.
     * Modifications to this collection are not backed up by the map or vice-versa.
     *
     * @return a collection view of the keys contained in this map
     */
    Collection<K> keys();

    /**
     * Returns a collection view of the values contained in this map.
     * Modifications to this collection are not backed up by the map or vice-versa.
     *
     * @return set a collection view of the values contained in this map
     */
    Collection<V> values();

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Removes all of the mappings from this map. The map will be empty after this call returns.
     */
    void clear();

}
