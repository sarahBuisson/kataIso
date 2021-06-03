package com.isograd.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SuperScanner {
    Scanner scanner;
    List<String> savedLine=new ArrayList<>();

    private void saveLine(String s){
        if(savedLine.size()<30)
        savedLine.add(s);
    }


    public SuperScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<Integer> nextInts(String separator){
        return Arrays.asList(nextLine().split(separator)).stream().map(i->Integer.parseInt(i.trim())).collect(Collectors.toList());
    }

    public List<String> nextStrings(String separator){
        return Arrays.asList(nextLine().split(separator));
    }

    public List<Character> nextChars() {
        List<Character> list = new ArrayList<Character>();
        for (char c : nextLine().toCharArray()) {
            list.add(c);
        }
        return list;
    }

    public void close() {
        scanner.close();
    }

    public IOException ioException() {
        return scanner.ioException();
    }

    public Pattern delimiter() {
        return scanner.delimiter();
    }

    public Scanner useDelimiter(Pattern pattern) {
        return scanner.useDelimiter(pattern);
    }

    public Scanner useDelimiter(String pattern) {
        return scanner.useDelimiter(pattern);
    }

    public Locale locale() {
        return scanner.locale();
    }

    public Scanner useLocale(Locale locale) {
        return scanner.useLocale(locale);
    }

    public int radix() {
        return scanner.radix();
    }

    public Scanner useRadix(int radix) {
        return scanner.useRadix(radix);
    }

    public MatchResult match() {
        return scanner.match();
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public String next() {
        return scanner.next();
    }

    public void remove() {
        scanner.remove();
    }

    public boolean hasNext(String pattern) {
        return scanner.hasNext(pattern);
    }

    public String next(String pattern) {
        return scanner.next(pattern);
    }

    public boolean hasNext(Pattern pattern) {
        return scanner.hasNext(pattern);
    }

    public String next(Pattern pattern) {
        return scanner.next(pattern);
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public String nextLine() {

        String s= scanner.nextLine();
        saveLine(s);
        return s;
    }

    public String findInLine(String pattern) {
        return scanner.findInLine(pattern);
    }

    public String findInLine(Pattern pattern) {
        return scanner.findInLine(pattern);
    }

    public String findWithinHorizon(String pattern, int horizon) {
        return scanner.findWithinHorizon(pattern, horizon);
    }

    public String findWithinHorizon(Pattern pattern, int horizon) {
        return scanner.findWithinHorizon(pattern, horizon);
    }

    public Scanner skip(Pattern pattern) {
        return scanner.skip(pattern);
    }

    public Scanner skip(String pattern) {
        return scanner.skip(pattern);
    }

    public boolean hasNextBoolean() {
        return scanner.hasNextBoolean();
    }

    public boolean nextBoolean() {
        return scanner.nextBoolean();
    }

    public boolean hasNextByte() {
        return scanner.hasNextByte();
    }

    public boolean hasNextByte(int radix) {
        return scanner.hasNextByte(radix);
    }

    public byte nextByte() {
        return scanner.nextByte();
    }

    public byte nextByte(int radix) {
        return scanner.nextByte(radix);
    }

    public boolean hasNextShort() {
        return scanner.hasNextShort();
    }

    public boolean hasNextShort(int radix) {
        return scanner.hasNextShort(radix);
    }

    public short nextShort() {
        return scanner.nextShort();
    }

    public short nextShort(int radix) {
        return scanner.nextShort(radix);
    }

    public boolean hasNextInt() {
        return scanner.hasNextInt();
    }

    public boolean hasNextInt(int radix) {
        return scanner.hasNextInt(radix);
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public int nextInt(int radix) {
        return scanner.nextInt(radix);
    }

    public boolean hasNextLong() {
        return scanner.hasNextLong();
    }

    public boolean hasNextLong(int radix) {
        return scanner.hasNextLong(radix);
    }

    public long nextLong() {
        return scanner.nextLong();
    }

    public long nextLong(int radix) {
        return scanner.nextLong(radix);
    }

    public boolean hasNextFloat() {
        return scanner.hasNextFloat();
    }

    public float nextFloat() {
        return scanner.nextFloat();
    }

    public boolean hasNextDouble() {
        return scanner.hasNextDouble();
    }

    public double nextDouble() {
        return scanner.nextDouble();
    }

    public boolean hasNextBigInteger() {
        return scanner.hasNextBigInteger();
    }

    public boolean hasNextBigInteger(int radix) {
        return scanner.hasNextBigInteger(radix);
    }

    public BigInteger nextBigInteger() {
        return scanner.nextBigInteger();
    }

    public BigInteger nextBigInteger(int radix) {
        return scanner.nextBigInteger(radix);
    }

    public boolean hasNextBigDecimal() {
        return scanner.hasNextBigDecimal();
    }

    public BigDecimal nextBigDecimal() {
        return scanner.nextBigDecimal();
    }

    public Scanner reset() {
        return scanner.reset();
    }

    public void forEachRemaining(Consumer<? super String> action) {
        scanner.forEachRemaining(action);
    }
}
