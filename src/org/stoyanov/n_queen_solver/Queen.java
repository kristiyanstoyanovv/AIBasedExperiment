package org.stoyanov.n_queen_solver;

import java.awt.*;
import java.util.ArrayList;

public class Queen {

    public static final String queenSymbol = "Q";

    private int [] currentPosition = new int[2];
    private ArrayList<Point> possibleMoves;
    private int hittableQueens;

    public Queen(int xPos, int yPos) {
        currentPosition[0] = xPos;
        currentPosition[1] = yPos;
        possibleMoves = new ArrayList<>();
    }

    public void setHittableQueens(int hittableQueens) {
        this.hittableQueens = hittableQueens;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public ArrayList<Point> getPossibleMoves() {
        return possibleMoves;
    }
}
