package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SoftCache<K, V> {
    private Map<K, SoftReference<V>> internalMap = new HashMap<>();
    private Function<K, V> loadValue;

    public SoftCache(Function<K, V> loadValue) {
        this.loadValue = loadValue;
    }

    public V getValue(K key) {
        V result;
        SoftReference<V> softReference = internalMap.get(key);
        V strongValue = softReference != null ? softReference.get() : null;
        if (strongValue != null) {
            result = strongValue;
        } else {
            result = loadValue.apply(key);
            internalMap.put(key, new SoftReference<>(result));
        }
        return result;
    }

    public SoftReference<V> put(K key, V value) {
        return internalMap.put(key, new SoftReference<>(value));
    }
}
