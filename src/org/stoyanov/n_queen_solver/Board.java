package org.stoyanov.n_queen_solver;

import java.awt.Point;

public class Board {

    protected int boardSize;

    protected String[][] gameBoard;
    protected Queen[] Queens;

    protected String queensPosAsString;

    protected int sumOfAllHittablePositions;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        gameBoard = new String[boardSize][boardSize];
        Queens = new Queen[boardSize];
    }

    protected void generateBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int x = 0; x < boardSize; x++) {
                gameBoard[i][x] = "_";
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int x = 0; x < boardSize; x++) {
                System.out.print(gameBoard[i][x] + "\t");
            }
            System.out.println();
        }
    }

    public int getSumOfAllHittablePositions() {
        return sumOfAllHittablePositions;
    }

    public void analyzeBoard() {
        findPossibleMoves();
        countHittableQueens();
    }

    public String getQueensPosAsString() {
        return queensPosAsString;
    }

    private void countHittableQueens() {
        for (Queen queen : Queens) {
            int temp = 0;
            for (Point p : queen.getPossibleMoves()) {
                if (gameBoard[p.x][p.y].equals("Q")) temp++;
            }
            queen.setHittableQueens(temp);
            sumOfAllHittablePositions += temp;
        }
    }

    private void findPossibleMoves() {
        Point tempPoint = new Point();
        for (Queen tempQueen : Queens) {
            int queenXPos = tempQueen.getCurrentPosition()[0];
            int queenYPos = tempQueen.getCurrentPosition()[1];
            for (int i = 1; i < boardSize; i++) {
                tempPoint.move(queenXPos + i, queenYPos);
                if (isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos, queenYPos + i);
                if (isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos);
                if (isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos, queenYPos - i);
                if (isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos + i, queenYPos + i);
                if (isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos - i);
                if (isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos + i, queenYPos - i);
                if (isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos + i);
                if (isCoordinateValid(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
            }
        }
    }

    public boolean isCoordinateValid(Point coordinate) {
        return coordinate.x >= 0 && coordinate.x <= boardSize-1 && coordinate.y >= 0 && coordinate.y <= boardSize-1;
    }


}
