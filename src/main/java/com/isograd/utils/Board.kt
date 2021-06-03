package com.isograd.utils

import java.util.HashMap
import java.util.function.BiFunction
import java.util.stream.Collectors
import java.util.Arrays
import java.awt.Point
import kotlin.jvm.JvmStatic
import java.io.PrintStream
import java.util.ArrayList

class Board<T> {
    val kases: MutableList<MutableList<T>> = ArrayList()

    constructor() {}
    constructor(width: Int, height: Int, deff: T) {
        for (i in 0 until width) {
            val line = ArrayList<T>()
            kases.add(line)
            for (j in 0 until height) line.add(deff)
        }
    }

    fun all(): Map<Point, T> {
        val ret = HashMap<Point, T>()
        for (j in kases.indices) {
            for (id in kases.get(j).indices) {
                ret[Point(id, j)] = get(id, j)
            }
        }
        return ret
    }

    /**
     * return the neighboor of a point in absolute value
     *
     * @param p
     * @param isNei
     * @return
     */
    fun absNeighbourOf(p: Point?, isNei: BiFunction<Point?, Point?, Boolean>): Map<Point, T> {
        val ret = HashMap<Point, T>()
        for (j in kases.indices) for (i in kases[j].indices) {
            if (isNei.apply(p, Point(i, j))) ret[Point(i, j)] = get(i, j)
        }
        return ret
    }

    fun labSolution(canPass: BiFunction<Point?, T, Boolean>, isDepart: BiFunction<Point?, T, Boolean>, isNei: BiFunction<Point?, Point?, Boolean>): Board<Int?> {
        val solution = Board<Int?>(kases.size, kases[0].size, null)
        var change = true
        while (change) {
            println(solution)
            change = false
            for (j in kases.indices) for (i in kases[j].indices) {
                val p = Point(i, j)
                if (isDepart.apply(p, get(p))) {
                    for ((key, value) in absNeighbourOf(p, isNei)) {
                        if (canPass.apply(key, value)) if (solution[key] == null || solution[key]!! > 1) solution[key] = 1
                    }
                }
                if (canPass.apply(p, get(p)) && solution[p] != null) {
                    val newVal = solution[p]!! + 1
                    for ((key, value) in absNeighbourOf(p, isNei)) {
                        if (canPass.apply(key, value)) if (solution[key] == null || solution[key]!! > newVal) solution[key] = newVal
                    }
                }
            }
        }
        return solution
    }

    operator fun get(p: Point): T {
        return kases[p.y][p.x]
    }

    operator fun get(x: Int, y: Int): T {
        return kases[y][x]
    }

    operator fun set(p: Point, `val`: T) {
        set(p.x, p.y, `val`)
    }

    operator fun set(x: Int, y: Int, `val`: T) {
        kases[y][x] = `val`
    }

    override fun toString(): String {
        return toString(";")
    }

    fun toString(separator: String?): String {
        return java.lang.String.join("\n",
                kases.stream().map { i: List<T>? ->
                    java.lang.String.join(separator, if (i != null) i.stream().map { i2: T? ->
                        i2?.toString() ?: ""
                    }.collect(Collectors.toList()) else ArrayList())
                }.collect(Collectors.toList()))
    }

    companion object {
        var isFullNeighboor: BiFunction<Point, Point, Boolean> = BiFunction { point, point2 -> point.distance(point2) < 2 }
        var isCrossNeighboor: BiFunction<Point, Point, Boolean> = BiFunction { point, point2 -> point.distance(point2) <= 1 }
    }
}