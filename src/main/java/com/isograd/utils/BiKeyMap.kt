package com.isograd.utils

import java.util.HashMap
import java.util.function.BiFunction
import java.util.stream.Collectors
import java.util.Arrays
import java.io.IOException
import java.util.Locale
import java.math.BigInteger
import java.math.BigDecimal
import kotlin.Throws
import kotlin.jvm.JvmStatic
import java.io.PrintStream

class BiKeyMap<K, V> {
    var map: MutableMap<K, MutableMap<K, V>> = HashMap()
    operator fun get(key1: K, key2: K): V? {
        val map2: Map<K, V>? = map[key1]
        return map2?.get(key2)
    }

    fun get1(key1: K): Collection<V> {
        return map[key1]!!.values
    }

    fun get2(key2: K): Collection<V?> {
        return map.values.stream().map { m2: Map<K, V> -> m2[key2] }.collect(Collectors.toList())
    }

    fun put(key1: K, key2: K, value: V): V? {
        var map2 = map[key1]
        if (map2 == null) {
            map2 = HashMap()
            map[key1] = map2
        }
        return map2.put(key2, value)
    }

    fun remove(key1: K, key2: K): V? {
        var map2 = map[key1]
        if (map2 == null) {
            map2 = HashMap()
            map[key1] = map2
        }
        return map2.remove(key2)
    }
}