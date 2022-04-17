package org.stoyanov.n_queen_solver;

public class GeneratedBoard extends Board {
    public GeneratedBoard(int boardSize) {
        super(boardSize);
        generateBoard();
    }

    @Override
    public void generateBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int x = 0; x < boardSize; x++) {
                gameBoard[i][x] = "_";
            }
        }
        generateQueens();
    }

    private void generateQueens() {
        queensPosAsString = "";
        for (int i = 0; i < boardSize; i++) {
            int queenRandomPosition = Utility.generateRandomInteger(0,boardSize-1);
            Queens[i] = new Queen(queenRandomPosition, i);
            queensPosAsString += queenRandomPosition;
            gameBoard[queenRandomPosition][i] = Queen.queenSymbol;
        }
        //System.out.println(queensPosAsString);
    }
}
