package com.Kris;

import java.awt.Point;

public class Board {

    protected static final int BOARD_WIDTH = 8;
    protected static final int BOARD_HEIGHT = 8;
    protected static final int QUEEN_COUNT = 8;

    protected String[][] gameBoard;
    protected Queen[] Queens;

    protected String queensPosAsString;
    protected int sumOfAllHittablePositions;

    public Board() {
        gameBoard = new String[BOARD_WIDTH][BOARD_HEIGHT];
        Queens = new Queen[QUEEN_COUNT];
    }

    protected void generateBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int x = 0; x < BOARD_HEIGHT; x++) {
                gameBoard[i][x] = "_";
            }
        }
    }

    public void displayBoard() {
        System.out.println(" \t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 0; i < BOARD_WIDTH; i++) {
            System.out.print(i + "\t");
            for (int x = 0; x < BOARD_HEIGHT; x++) {
                System.out.print(gameBoard[i][x] + "\t");
            }
            System.out.println();
        }
    }

    public void analyzeBoard() {
        findPossibleMoves();
        countHittableQueens();
    }

    private void countHittableQueens() {
        for (int i = 0; i < Queens.length; i++) {
            //System.out.print("Queen " + i);
            int temp = 0;
            for (Point p : Queens[i].getPossibleMoves()) {
                if (gameBoard[p.x][p.y].equals("Q")) temp++;
            }
            //System.out.print(": "+temp+"\n");
            Queens[i].setHittableQueens(temp);
            sumOfAllHittablePositions += temp;
        }
        System.out.println(sumOfAllHittablePositions);
    }

    private void findPossibleMoves() {
        Point tempPoint = new Point();
        for (Queen tempQueen : Queens) {
            int queenXPos = tempQueen.getCurrentPosition()[0];
            int queenYPos = tempQueen.getCurrentPosition()[1];
            for (int i = 1; i < 8; i++) {
                tempPoint.move(queenXPos + i, queenYPos);
                if (Board.isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos, queenYPos + i);
                if (Board.isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos);
                if (Board.isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos, queenYPos - i);
                if (Board.isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos + i, queenYPos + i);
                if (Board.isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos - i);
                if (Board.isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos + i, queenYPos - i);
                if (Board.isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos + i);
                if (Board.isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
            }
        }
    }

    public static boolean isCoordinateValid(Point coordinate) {
        return coordinate.x >= 0 && coordinate.x <= 7 && coordinate.y >= 0 && coordinate.y <= 7;
    }


}
