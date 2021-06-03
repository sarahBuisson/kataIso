package com.isograd.utils

import java.util.function.BiFunction
import java.util.stream.Collectors
import java.io.IOException
import java.math.BigInteger
import java.math.BigDecimal
import java.util.*
import java.util.function.Consumer
import java.util.regex.MatchResult
import java.util.regex.Pattern

class SuperScanner(var scanner: Scanner) {
    var savedLine: MutableList<String> = ArrayList()
    private fun saveLine(s: String) {
        if (savedLine.size < 30) savedLine.add(s)
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