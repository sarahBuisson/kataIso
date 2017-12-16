/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package com.isograd.exercise;


import java.awt.*;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IsoContest {


    public static void mainContent(Scanner scanner) throws Exception {
        SuperScanner sc = new SuperScanner(scanner);


        int val = sc.nextInts(" ").get(0);



        List<String> solution = new ArrayList<>();
        while (sc.hasNextLine()) {
            List<Integer> vals = sc.nextInts(" ");
            //List<Integer> vals = sc.nextStrings(" ");


        }


        //if(solu.size()>1)
        //  sout(String.join("\n",sc.savedLine));

        sout("noSolYet");
       // sout((String.join(" ",solution));
        //for(String solution:solution)
          //  sout(solution);

        //        sout(String.join(" ",best));
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }


    public static void main(String[] argv) throws Exception {

        Scanner sc = new Scanner(System.in);
        mainContent(sc);


    }

    public static void sout(Object message) {
        if (debug)
            System.out.println("sout" + message);
        out.println(message);

    }

    public static void soutn(Object message) {
        if (debug)
            System.out.println("sout" + message);
        out.print(message);

    }

    public static void log(Object message) {

        if (debug)
            System.out.println("log" + message.toString());

    }

    public static boolean debug = false;

    public static PrintStream out = System.out;

    public static void initDebug(PrintStream outs) {
        debug = true;
        out = outs;

    }


    public static class Board<T> {

        public final List<List<T>> kases = new ArrayList<>();

        public Board() {
        }

        public Board(int width, int height, T deff) {


            for (int i = 0; i < width; i++) {
                ArrayList<T> line = new ArrayList<>();
                kases.add(line);
                for (int j = 0; j < height; j++)
                    line.add(deff);
            }


        }

        public Map<Point, T> all() {
            HashMap<Point, T> ret = new HashMap<>();
            for (int j = 0; j < kases.size(); j++)
                for (int i = 0; i < kases.get(j).size(); i++) {
                    ret.put(new Point(i, j), get(i, j));

                }
            return ret;
        }

        /**
         * return the neighboor of a point in absolute value
         *
         * @param p
         * @param isNei
         * @return
         */
        public Map<Point, T> absNeighbourOf(final Point p, BiFunction<Point, Point, Boolean> isNei) {

            HashMap<Point, T> ret = new HashMap<>();
            for (int j = 0; j < kases.size(); j++)
                for (int i = 0; i < kases.get(j).size(); i++) {
                    if (isNei.apply(p, new Point(i, j)))
                        ret.put(new Point(i, j), get(i, j));

                }
            return ret;
        }

        public Board<Integer> labSolution(BiFunction<Point, T, Boolean> canPass, BiFunction<Point, T, Boolean> isDepart, BiFunction<Point, Point, Boolean> isNei) {
            Board<Integer> solution = new Board<Integer>(this.kases.size(), this.kases.get(0).size(), null);
            boolean change = true;
            while (change) {
                change = false;
                for (int j = 0; j < kases.size(); j++)
                    for (int i = 0; i < kases.get(j).size(); i++) {
                        Point p = new Point(i, j);
                        if (isDepart.apply(p, get(p))) {
                            solution.set(p, 0);
                            for (Map.Entry<Point, T> e : absNeighbourOf(p, isNei).entrySet()) {
                                if (canPass.apply(e.getKey(), e.getValue()))
                                    if (solution.get(e.getKey()) == null || solution.get(e.getKey()) > 1) {
                                        solution.set(e.getKey(), 1);
                                        change = true;
                                    }
                            }


                        }
                        if (canPass.apply(p, get(p)) && solution.get(p) != null) {
                            Integer newVal = solution.get(p) + 1;
                            for (Map.Entry<Point, T> e : absNeighbourOf(p, isNei).entrySet()) {
                                if (canPass.apply(e.getKey(), e.getValue()))
                                    if (solution.get(e.getKey()) == null || solution.get(e.getKey()) > newVal) {
                                        solution.set(e.getKey(), newVal);
                                        change = true;
                                    }
                            }
                        }
                    }
            }
            return solution;
        }


        public T get(Point p) {
            return kases.get(p.y).get(p.x);
        }

        public T get(int x, int y) {
            return kases.get(y).get(x);
        }

        public void set(Point p, T val) {
            set(p.x, p.y, val);
        }

        public void set(int x, int y, T val) {
            kases.get(y).set(x, val);
        }

        public static BiFunction<Point, Point, Boolean> isFullNeighboor = new BiFunction<Point, Point, Boolean>() {
            @Override
            public Boolean apply(Point point, Point point2) {
                return point.distance(point2) < 2;
            }
        };
        public static BiFunction<Point, Point, Boolean> isCrossNeighboor = new BiFunction<Point, Point, Boolean>() {
            @Override
            public Boolean apply(Point point, Point point2) {
                return point.distance(point2) <= 1;
            }
        };

        public String toString() {
            return toString(";");
        }

        public String toString(String separator) {
            return String.join("\n",
                    kases.stream().map(i -> String.join(separator, i != null ? i.stream().map(i2 -> i2 != null ? i2.toString() : " ").collect(Collectors.toList()) : new ArrayList<>())).collect(Collectors.toList()));
        }
    }

    public static class SuperScanner {
        Scanner scanner;
        List<String> savedLine = new ArrayList<>();

        private void saveLine(String s) {
            if (savedLine.size() < 130)
                savedLine.add(s);
        }


        public SuperScanner(Scanner scanner) {
            this.scanner = scanner;
        }

        public List<Integer> nextInts(String separator) {
            return Arrays.asList(nextLine().split(separator)).stream().map(i -> Integer.parseInt(i.trim())).collect(Collectors.toList());
        }

        public List<String> nextStrings(String separator) {
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

            String s = scanner.nextLine();
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


}
