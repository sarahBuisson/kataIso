package com.isograd.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BiKeyMap<K, V> {
    Map<K, Map<K, V>> map = new HashMap<>();

    public BiKeyMap() {

    }

    public V get(K key1, K key2) {
        Map<K, V> map2 = map.get(key1);
        return map2 != null ? map2.get(key2) : null;
    }

    public Collection<V> get1(K key1) {
        return map.get(key1).values();

    }

    public Collection<V> get2(K key2) {
        return map.values().stream().map(m2 -> m2.get(key2)).collect(Collectors.toList());
    }

    public V put(K key1, K key2, V value) {
        Map<K, V> map2 = map.get(key1);
        if (map2 == null) {
            map2 = new HashMap<>();
            map.put(key1, map2);
        }
        return map2.put(key2, value);
    }


    public V remove(K key1, K key2) {
        Map<K, V> map2 = map.get(key1);
        if (map2 == null) {
            map2 = new HashMap<>();
            map.put(key1, map2);
        }
        return map2.remove(key2);
    }

}
