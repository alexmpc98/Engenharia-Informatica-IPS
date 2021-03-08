package pt.pa.adts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This implementations stores the mappings in and instance of {@link List}.
 * The equality of keys is verified by {@link Object#equals(Object)}.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class MapList<K,V> implements Map<K,V> {

    private List<KeyValue> mappings;

    public MapList() {
        mappings = new ArrayList<>();
    }

    @Override
    public V put(K key, V value) throws NullPointerException {
        if(key == null) throw new NullPointerException("This implementation does not support null keys.");

        for(KeyValue kv : mappings) {
            if(kv.key.equals(key)) {
                V old = kv.value;
                kv.value = value;
                return old;
            }
        }

        mappings.add(new KeyValue(key,value));
        return null;
    }

    @Override
    public V get(K key) throws NullPointerException {
        if(key == null) throw new NullPointerException("This implementation does not support null keys.");

        for(KeyValue kv : mappings) {
            if(kv.key.equals(key)) return kv.value;
        }
        return null;
    }

    @Override
    public V remove(K key) throws NullPointerException {
        if(key == null) throw new NullPointerException("This implementation does not support null keys.");

        int keyIndex = -1;
        for(int i=0; i<size(); i++) {
            if(mappings.get(i).key.equals(key)) {
                keyIndex = i;
                break;
            }
        }

        if(keyIndex != -1) {
            V value = mappings.get(keyIndex).value;
            mappings.remove(keyIndex);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public boolean containsKey(K key) throws NullPointerException {
        if(key == null) throw new NullPointerException("This implementation does not support null keys.");

        for(KeyValue kv : mappings) {
            if(kv.key.equals(key)) return true;
        }
        return false;
    }

    @Override
    public Collection<K> keys() {
        List<K> keys = new ArrayList<>();
        for(KeyValue kv : mappings) {
            keys.add( kv.key );
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for(KeyValue kv : mappings) {
            values.add( kv.value );
        }
        return values;
    }

    @Override
    public int size() {
        return mappings.size();
    }

    @Override
    public boolean isEmpty() {
        return mappings.isEmpty();
    }

    @Override
    public void clear() {
        mappings.clear();
    }

    @Override
    public String toString() {
        return "MapList{" + mappings + '}';
    }

    private class KeyValue {
        private K key;
        private V value;

        public KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
