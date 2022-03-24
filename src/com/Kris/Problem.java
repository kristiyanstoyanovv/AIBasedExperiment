package com.Kris;

import java.util.ArrayList;

public class Problem {
    private Board [] populationBoards;

    public Problem() {
        initializeGamingBoards();
    }

    public static void AIGeneticAlgorithm() {
        if (Utility.generateRandomNumber(0,1) == 0) {

        } else {

        }

    }

    public void initializeGamingBoards() {
        generateBoards(Utility.generateRandomNumber(0,20));
        analyzeAllBoards();
    }

    public void analyzeAllBoards() {

    }

    public void generateBoards(int randomNumber) {
        populationBoards = new Board[randomNumber];
        for (int i = 0; i < randomNumber; i++) {
          //  populationBoards.add(new Board());
        }
    }

}
