package com.Kris;

public class Problem {
    private GeneratedBoard [] populationBoards;

    public void initializeGamingBoards() {
        generateBoards(4);
    }


    public void generateBoards(int boardsCount) {
        populationBoards = new GeneratedBoard[boardsCount];
        for (int i = 0; i < boardsCount; i++) {
            populationBoards[i] = new GeneratedBoard();
            populationBoards[i].analyzeBoard();
        }
    }

}
