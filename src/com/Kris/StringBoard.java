package com.Kris;

public class StringBoard extends Board {

    public StringBoard(String queensPositions) {
        super();
        generateBoard();
        this.queensPosAsString = queensPositions;
        setupQueensByString(queensPositions);
        analyzeBoard();
    }

    public void setupQueensByString(String queenPositions) {
        for (int i = 0; i < queenPositions.length(); i++) {
            int temp = Character.getNumericValue(queenPositions.charAt(i));
            gameBoard[temp][i] = "Q";
            Queens[i] = new Queen(temp,i);
        }
    }


}
