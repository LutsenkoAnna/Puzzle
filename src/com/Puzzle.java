package com;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Anna on 11.04.2017.
 */
public class Puzzle {
    private List<Cell> puzzle;

    Puzzle() {
        puzzle = new ArrayList<>();
        puzzle.add(new Cell(1, 3, "", true));
        puzzle.add(new Cell(3, 3, "", true));
        puzzle.add(new Cell(0, 2, "white", false));
        puzzle.add(new Cell(2, 2, "", true));
        puzzle.add(new Cell(4, 2, "white", false));
        puzzle.add(new Cell(1, 1, "", true));
        puzzle.add(new Cell(3, 1, "", true));
        puzzle.add(new Cell(0, 0, "black", false));
        puzzle.add(new Cell(2, 0, "", true));
        puzzle.add(new Cell(4, 0, "black", false));
    }

    public void print() {
        String tabs = "";
        int start = 0;
        for (int j = 0; j < puzzle.size(); ++j) {
            for (int i = start; i < puzzle.get(j).getCoordX(); ++i)
                tabs += " ";
            start = puzzle.get(j).getCoordX();
            if (!puzzle.get(j).getIsEmpty()) {
                System.out.print(tabs + puzzle.get(j).getColor().charAt(0));
            }
            else {
                System.out.print(tabs + "_");
            }
            tabs = "";
            if (j == (puzzle.size() - 1) || puzzle.get(j).getCoordY() != puzzle.get(j + 1).getCoordY()) {
                start = 0;
                System.out.println();
            }
        }
    }

    private boolean checkConflicts(Cell cell) {
        for (Cell c: puzzle) {
            if (c.getCoordY() != cell.getCoordY() || c.getCoordX() != cell.getCoordX()) {
                if (!c.getIsEmpty() && (c.getColor().equals(cell.getColor())) &&
                        (Math.abs(c.getCoordY() - cell.getCoordY()) == Math.abs(c.getCoordX() - cell.getCoordX()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }



    public void algorithm() {
        PriorityQueue<Map<Integer,Cell>> allSteps = new PriorityQueue<Map<Integer, Cell>>() {
            public int compare(Map.Entry<Integer, Cell> e1, Map.Entry<Integer, Cell> e2) {
                return e2.getKey() - e1.getKey();
            };
        };
        Map<Integer, Cell> steps = new HashMap<>();
        for (Cell c: puzzle) {
            c.makeStep();
            if (!c.getIsEmpty()) {
                for (Cell possible : c.getPossibleSteps()) {
                    //TODO Сделать мапу с клетка - расстояние. И это запихнуть в приорити кью
                    if (!checkConflicts(possible)) {
                        steps.put(possible.countDistance(), possible);
                        allSteps.add(steps);
                    }
                }
            }
        }
    }

}
