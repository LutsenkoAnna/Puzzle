package com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 11.04.2017.
 */
public class Cell {
    private int coordX;
    private int coordY;
    private String color; // 'black', 'white', ''
    private boolean isEmpty;
    private List<Cell> possibleSteps;

    public Cell(int x, int y, String c, boolean empty) {
        this.coordX = x;
        this.coordY = y;
        this.color = c;
        this.isEmpty = empty;
    }

    //Надо менять не координаты, а только цвет

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public String getColor() {
        return color;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public List<Cell> getPossibleSteps() {
        return possibleSteps;
    }

    public void makeStep() {
        possibleSteps = new ArrayList<>();
        // Разобраться с номером
        if ((coordX - 1) >= 0 && (coordY - 1) >= 0)
            possibleSteps.add(new Cell(this.coordX - 1, this.coordY - 1, this.color, false));
        if ((coordX + 1) < 3 && (coordY + 1) < 4)
            possibleSteps.add(new Cell(this.coordX + 1, this.coordY + 1,  this.color, false));
        if ((coordX - 1) >= 0 && (coordY + 1) < 4)
            possibleSteps.add(new Cell(this.coordX - 1, this.coordY + 1,  this.color, false));
        if ((coordX + 1) < 3 && (coordY - 1) >= 0)
            possibleSteps.add(new Cell(this.coordX + 1, this.coordY - 1,  this.color, false));
    }

    //Подумать
    public int countDistance() {
        if (color.equals("black")) {
            return Math.min(Math.abs(coordX - 0) + Math.abs(2 - coordY), Math.abs(4 - coordX) + Math.abs(2 - coordY));
        }
        else if (color.equals("white")) {
            return Math.min(Math.abs(coordX - 0) + Math.abs(0 - coordY), Math.abs(4 - coordX) + Math.abs(0 - coordY));
        }
        return 0;
    }
}
