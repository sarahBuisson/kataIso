package com.isograd.exercise

import java.util.*


fun main(args: Array<String>) {
    println("r")
    try {
        val input = generateSequence(::readLine)
        val lines = input.toList()
        System.err.println("___")
        println("r")
        //   System.err.println(lines.get(0))

        val size = lines.get(0).toInt()
        val orbit = lines.get(1)

        // val size=20
        // val orbit = "CACBBBCABCCABACBCBBC"
        System.err.println(orbit)

        val longOrbit = orbit + orbit
        System.err.println("comput long orbit")
        var soluce = 0;
        for (i in 0..size) {
            val o1 = longOrbit.substring(i, i + size / 2)
            val o2 = longOrbit.substring(i + size / 2, i + size)

            System.err.println("comput sub orbit")
            val distinct1 = count(o1)
            val distinct2 = count(o2)
            System.err.println("comput distinct")
            if (distinct1.all { it.value == distinct2.getOrDefault(it.key, 0) })
                soluce++


        }

        println("" + ((soluce - soluce % 2)))
    } catch (e: Exception) {
        System.err.println(e)
    }

}

fun count(o1: String): Map<Char, Int> {
    val m = mutableMapOf<Char, Int>()
    o1.forEach { m.put(it, m.getOrDefault(it, 0) + 1) }
    return m;
}
