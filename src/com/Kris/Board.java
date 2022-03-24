package com.Kris;

import java.awt.*;

public class Board {
    private final String[][] gameBoard;
    private final int boardWidth;
    private final int boardHeight;
    private String queenPosAsString;
    private Queen[] Queens;
    private int sumOfAllHittablePositions;

    public Board() {
        this.boardWidth = 8;
        this.boardHeight = 8;
        gameBoard = new String[boardWidth][boardHeight];
        generateBoard();
        analyzeBoard();
    }

    public Board(String queenPositions) {
        if (queenPositions.length() > 8) {
            gameBoard = null;
            boardWidth = -1;
            boardHeight = -1;
            return;
        }
        this.boardWidth = 8;
        this.boardHeight = 8;
        gameBoard = new String[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int x = 0; x < boardHeight; x++) {
                gameBoard[i][x] = "_";
            }
        }
        Queens = new Queen[boardWidth];
        for (int i = 0; i < queenPositions.length(); i++) {
            int temp = Character.getNumericValue(queenPositions.charAt(i));
            gameBoard[temp][i] = "Q";
            Queens[i] = new Queen(temp,i);
        }
        analyzeBoard();
    }

    public String[][] getBoard() {
        return gameBoard;
    }

    public void generateBoard() {
        for (int i = 0; i < boardWidth; i++) {
            for (int x = 0; x < boardHeight; x++) {
                gameBoard[i][x] = "_";
            }
        }
        Queens = new Queen[boardWidth];
        generateQueens();
    }

    private void generateQueens() {
        queenPosAsString = "";
        for (int i = 0; i < boardWidth; i++) {
            int queenRandomPosition = Utility.generateRandomNumber(0,7);
            Queens[i] = new Queen(queenRandomPosition, i);
            queenPosAsString += queenRandomPosition;
            gameBoard[queenRandomPosition][i] = Queen.queenSymbol;
        }
        System.out.println(queenPosAsString);
    }

    public void displayBoard() {
        System.out.println(" \t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 0; i < boardWidth; i++) {
            System.out.print(i + "\t");
            for (int x = 0; x < boardHeight; x++) {
                System.out.print(gameBoard[i][x] + "\t");
            }
            System.out.println();
        }
    }

    public void analyzeBoard() {
        findPossibleMoves();
        countHittableQueens();
    }

    public void countHittableQueens() {
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

    public void findPossibleMoves() {
        Point tempPoint = new Point();
        for (Queen tempQueen : Queens) {
            int queenXPos = tempQueen.getCurrentPosition()[0];
            int queenYPos = tempQueen.getCurrentPosition()[1];
            for (int i = 1; i < 8; i++) {
                tempPoint.move(queenXPos + i, queenYPos);
                if (Board.isValidCoordinate(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos, queenYPos + i);
                if (Board.isValidCoordinate(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos);
                if (Board.isValidCoordinate(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos, queenYPos - i);
                if (Board.isValidCoordinate(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos + i, queenYPos + i);
                if (Board.isValidCoordinate(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos - i);
                if (Board.isValidCoordinate(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos + i, queenYPos - i);
                if (Board.isValidCoordinate(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
                tempPoint.move(queenXPos - i, queenYPos + i);
                if (Board.isValidCoordinate(tempPoint)) tempQueen.getPossibleMoves().add(new Point(tempPoint));
            }
        }
    }

    public static boolean isValidCoordinate(Point coordinate) {
        return coordinate.x >= 0 && coordinate.x <= 7 && coordinate.y >= 0 && coordinate.y <= 7;
    }


}
