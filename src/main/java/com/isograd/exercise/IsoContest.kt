/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 * IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 */
package com.isograd.exercise

import java.util.function.BiFunction
import java.util.stream.Collectors
import java.io.IOException
import java.math.BigInteger
import java.math.BigDecimal
import kotlin.Throws
import java.awt.Point
import kotlin.jvm.JvmStatic
import java.io.PrintStream
import java.lang.Exception
import java.util.*
import java.util.function.Consumer
import java.util.regex.MatchResult
import java.util.regex.Pattern

class IsoContest {
    @Throws(Exception::class)
    fun mainContent(scanner: Scanner) {
        println("errr")
        val sc = SuperScanner(scanner)
        val `val` = sc.nextInts(" ")[0]
        val solution: List<String> = ArrayList()
        while (sc.hasNextLine()) {
            val vals = sc.nextInts(" ")
            //List<Integer> vals = sc.nextStrings(" ");
        }
        println("errrr")

        //if(solu.size()>1)
        //  sout(String.join("\n",sc.savedLine));
        sout("noSolYet")
        // sout((String.join(" ",solution));
        //for(String solution:solution)
        //  sout(solution);

        //        sout(String.join(" ",best));
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    fun binC(s: String): String {
        var str = ""
        for (i in 0 until s.length / 8) {
            val a = s.substring(8 * i, (i + 1) * 8).toInt(2)
            str += a.toChar()
        }
        return str
    }


    fun sout(message: Any) {
        if (debug) println("sout$message")
        out.println(message)
    }

    fun soutn(message: Any) {
        if (debug) println("sout$message")
        out.print(message)
    }

    fun log(message: Any) {
        if (debug) println("log$message")
    }

    var debug = true
    var out = System.out
    fun initDebug(outs: PrintStream) {
        debug = true
        out = outs
    }

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
            for (j in kases.indices) for (i in kases[j].indices) {
                ret[Point(i, j)] = get(i, j)
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
                change = false
                for (j in kases.indices) for (i in kases[j].indices) {
                    val p = Point(i, j)
                    if (isDepart.apply(p, get(p))) {
                        solution[p] = 0
                        for ((key, value) in absNeighbourOf(p, isNei)) {
                            if (canPass.apply(key, value)) if (solution[key] == null || solution[key]!! > 1) {
                                solution[key] = 1
                                change = true
                            }
                        }
                    }
                    if (canPass.apply(p, get(p)) && solution[p] != null) {
                        val newVal = solution[p]!! + 1
                        for ((key, value) in absNeighbourOf(p, isNei)) {
                            if (canPass.apply(key, value)) if (solution[key] == null || solution[key]!! > newVal) {
                                solution[key] = newVal
                                change = true
                            }
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
                            i2?.toString() ?: " "
                        }.collect(Collectors.toList()) else ArrayList())
                    }.collect(Collectors.toList()))
        }

        companion object {
            var isFullNeighboor: BiFunction<Point, Point, Boolean> = BiFunction { point, point2 -> point.distance(point2) < 2 }
            var isCrossNeighboor: BiFunction<Point, Point, Boolean> = BiFunction { point, point2 -> point.distance(point2) <= 1 }
        }
    }

    class SuperScanner(var scanner: Scanner) {
        var savedLine: MutableList<String> = ArrayList()
        private fun saveLine(s: String) {
            if (savedLine.size < 130) savedLine.add(s)
        }

        fun nextInts(separator: String): List<Int> {
            return Arrays.asList(*nextLine().split(separator.toRegex()).toTypedArray()).stream().map { i: String -> i.trim { it <= ' ' }.toInt() }.collect(Collectors.toList())
        }

        fun nextStrings(separator: String): List<String> {
            return Arrays.asList(*nextLine().split(separator.toRegex()).toTypedArray())
        }

        fun nextChars(): List<Char> {
            val list: MutableList<Char> = ArrayList()
            for (c in nextLine().toCharArray()) {
                list.add(c)
            }
            return list
        }

        fun close() {
            scanner.close()
        }

        fun ioException(): IOException {
            return scanner.ioException()
        }

        fun delimiter(): Pattern {
            return scanner.delimiter()
        }

        fun useDelimiter(pattern: Pattern?): Scanner {
            return scanner.useDelimiter(pattern)
        }

        fun useDelimiter(pattern: String?): Scanner {
            return scanner.useDelimiter(pattern)
        }

        fun locale(): Locale {
            return scanner.locale()
        }

        fun useLocale(locale: Locale?): Scanner {
            return scanner.useLocale(locale)
        }

        fun radix(): Int {
            return scanner.radix()
        }

        fun useRadix(radix: Int): Scanner {
            return scanner.useRadix(radix)
        }

        fun match(): MatchResult {
            return scanner.match()
        }

        operator fun hasNext(): Boolean {
            return scanner.hasNext()
        }

        operator fun next(): String {
            return scanner.next()
        }

        fun remove() {
            scanner.remove()
        }

        fun hasNext(pattern: String?): Boolean {
            return scanner.hasNext(pattern)
        }

        fun next(pattern: String?): String {
            return scanner.next(pattern)
        }

        fun hasNext(pattern: Pattern?): Boolean {
            return scanner.hasNext(pattern)
        }

        fun next(pattern: Pattern?): String {
            return scanner.next(pattern)
        }

        fun hasNextLine(): Boolean {
            return scanner.hasNextLine()
        }

        fun nextLine(): String {
            val s = scanner.nextLine()
            saveLine(s)
            return s
        }

        fun findInLine(pattern: String?): String {
            return scanner.findInLine(pattern)
        }

        fun findInLine(pattern: Pattern?): String {
            return scanner.findInLine(pattern)
        }

        fun findWithinHorizon(pattern: String?, horizon: Int): String {
            return scanner.findWithinHorizon(pattern, horizon)
        }

        fun findWithinHorizon(pattern: Pattern?, horizon: Int): String {
            return scanner.findWithinHorizon(pattern, horizon)
        }

        fun skip(pattern: Pattern?): Scanner {
            return scanner.skip(pattern)
        }

        fun skip(pattern: String?): Scanner {
            return scanner.skip(pattern)
        }

        fun hasNextBoolean(): Boolean {
            return scanner.hasNextBoolean()
        }

        fun nextBoolean(): Boolean {
            return scanner.nextBoolean()
        }

        fun hasNextByte(): Boolean {
            return scanner.hasNextByte()
        }

        fun hasNextByte(radix: Int): Boolean {
            return scanner.hasNextByte(radix)
        }

        fun nextByte(): Byte {
            return scanner.nextByte()
        }

        fun nextByte(radix: Int): Byte {
            return scanner.nextByte(radix)
        }

        fun hasNextShort(): Boolean {
            return scanner.hasNextShort()
        }

        fun hasNextShort(radix: Int): Boolean {
            return scanner.hasNextShort(radix)
        }

        fun nextShort(): Short {
            return scanner.nextShort()
        }

        fun nextShort(radix: Int): Short {
            return scanner.nextShort(radix)
        }

        fun hasNextInt(): Boolean {
            return scanner.hasNextInt()
        }

        fun hasNextInt(radix: Int): Boolean {
            return scanner.hasNextInt(radix)
        }

        fun nextInt(): Int {
            return scanner.nextInt()
        }

        fun nextInt(radix: Int): Int {
            return scanner.nextInt(radix)
        }

        fun hasNextLong(): Boolean {
            return scanner.hasNextLong()
        }

        fun hasNextLong(radix: Int): Boolean {
            return scanner.hasNextLong(radix)
        }

        fun nextLong(): Long {
            return scanner.nextLong()
        }

        fun nextLong(radix: Int): Long {
            return scanner.nextLong(radix)
        }

        fun hasNextFloat(): Boolean {
            return scanner.hasNextFloat()
        }

        fun nextFloat(): Float {
            return scanner.nextFloat()
        }

        fun hasNextDouble(): Boolean {
            return scanner.hasNextDouble()
        }

        fun nextDouble(): Double {
            return scanner.nextDouble()
        }

        fun hasNextBigInteger(): Boolean {
            return scanner.hasNextBigInteger()
        }

        fun hasNextBigInteger(radix: Int): Boolean {
            return scanner.hasNextBigInteger(radix)
        }

        fun nextBigInteger(): BigInteger {
            return scanner.nextBigInteger()
        }

        fun nextBigInteger(radix: Int): BigInteger {
            return scanner.nextBigInteger(radix)
        }

        fun hasNextBigDecimal(): Boolean {
            return scanner.hasNextBigDecimal()
        }

        fun nextBigDecimal(): BigDecimal {
            return scanner.nextBigDecimal()
        }

        fun reset(): Scanner {
            return scanner.reset()
        }

        fun forEachRemaining(action: Consumer<in String?>) {
            scanner.forEachRemaining(action)
        }
    }

    class BiKeyMap<K, V> {
        var map: MutableMap<K, MutableMap<K, V>> = HashMap()
        operator fun get(key1: K, key2: K): V? {
            val map2: Map<K, V>? = map[key1]
            return map2?.get(key2)
        }

        fun get1(key1: K): Collection<V> {
            return map[key1]!!.values
        }

        fun get2(key2: K): List<V?> {
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

        fun values(): Collection<V> {
            return map.values.stream().flatMap { m2: Map<K, V> -> m2.values.stream() }.filter { i: V? -> i != null }.collect(Collectors.toList())
        }
    }
}

fun main(argv: Array<String>) {
    val sc = Scanner(System.`in`)
    println("rrr")
    IsoContest().mainContent(sc)
}