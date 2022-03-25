package com.Kris;

public class GeneratedBoard extends Board {
    public GeneratedBoard() {
        super();
        generateBoard();
    }

    @Override
    public void generateBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int x = 0; x < BOARD_HEIGHT; x++) {
                gameBoard[i][x] = "_";
            }
        }
        generateQueens();
    }

    private void generateQueens() {
        queensPosAsString = "";
        for (int i = 0; i < BOARD_WIDTH; i++) {
            int queenRandomPosition = Utility.generateRandomNumber(0,7);
            Queens[i] = new Queen(queenRandomPosition, i);
            queensPosAsString += queenRandomPosition;
            gameBoard[queenRandomPosition][i] = Queen.queenSymbol;
        }
        System.out.println(queensPosAsString);
    }
}
