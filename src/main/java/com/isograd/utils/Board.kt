package com.isograd.utils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Board<T> {

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
            for (int i = 0; i < kases.get(i).size(); i++) {
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
        Board<Integer> solution = new Board<Integer>( this.kases.size(),this.kases.get(0).size(), null);
        boolean change = true;
        while (change) {
            System.out.println(solution);
            change = false;
            for (int j = 0; j < kases.size(); j++)
                for (int i = 0; i < kases.get(j).size(); i++) {
                    Point p = new Point(i, j);
                    if (isDepart.apply(p, get(p))) {
                        for (Map.Entry<Point, T> e : absNeighbourOf(p, isNei).entrySet()) {
                            if (canPass.apply(e.getKey(), e.getValue()))
                                if (solution.get(e.getKey()) == null || solution.get(e.getKey()) > 1)
                                    solution.set(e.getKey(), 1);
                        }


                    }
                    if (canPass.apply(p, get(p)) && solution.get(p) != null) {
                        Integer newVal = solution.get(p) + 1;
                        for (Map.Entry<Point, T> e : absNeighbourOf(p, isNei).entrySet()) {
                            if (canPass.apply(e.getKey(), e.getValue()))
                                if (solution.get(e.getKey()) == null || solution.get(e.getKey()) > newVal)
                                    solution.set(e.getKey(), newVal);
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
                kases.stream().map(i -> String.join(separator, i != null ? i.stream().map(i2 -> i2!=null?i2.toString():"").collect(Collectors.toList()) : new ArrayList<>())).collect(Collectors.toList()));
    }
}
