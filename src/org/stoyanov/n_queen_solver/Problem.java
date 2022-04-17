package org.stoyanov.n_queen_solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Problem {

    private List<Board> populationBoards = new ArrayList<>();
    private int populationSize;
    private int boardSize;


    public Problem (int populationSize, int boardSize) {
        this.populationSize = populationSize;
        this.boardSize = boardSize;
        generateBoards();
    }

    public Board getBestBoard() {
        return populationBoards.get(0);
    }

    public boolean solveProblem() {

        if (populationBoards.get(0).sumOfAllHittablePositions == 0) {
            populationBoards.get(0).displayBoard();
            return true;
        }
        System.out.println("Population size: " + populationSize);
        populationBoards = crossOverFunction();
        sortPopulation();
        return false;
    }

    public void sortPopulation() {
        Collections.sort(populationBoards, new Comparator<Board>() {
            @Override
            public int compare(Board b1, Board b2) {
                if (b1.getSumOfAllHittablePositions() == b2.getSumOfAllHittablePositions())
                    return 0;
                else if (b1.getSumOfAllHittablePositions() > b2.getSumOfAllHittablePositions())
                    return 1;
                else
                    return -1;
            }
        });
    }


    public Board fitnessFunction() {
        double maxHitsCount = boardSize * (boardSize-1);
        while (true) {
            for (Board board : populationBoards) {
                if (Utility.generateRandomDouble() > (double) board.getSumOfAllHittablePositions() / maxHitsCount) {
                    return board;
                }
            }
        }
    }

    public ArrayList<Board> crossOverFunction() {
        ArrayList<Board> nextGenPopulation = new ArrayList();
        for(int i = 0; i < populationBoards.size(); i+=2) {
            int randomNumber = Utility.generateRandomInteger(0, boardSize);
            String parentXS = fitnessFunction().getQueensPosAsString();
            String parentYS = fitnessFunction().getQueensPosAsString();
            String childX = parentXS.substring(0, randomNumber) +
                    parentYS.substring(randomNumber);
            String childY = parentYS.substring(0, randomNumber) +
                    parentXS.substring(randomNumber);
            if (Utility.generateRandomDouble() <= 0.8) {
                nextGenPopulation.add(new StringBoard(mutateChild(childX)));
                nextGenPopulation.add(new StringBoard(mutateChild(childY)));
            } else {
                nextGenPopulation.add(new StringBoard(childX));
                nextGenPopulation.add(new StringBoard(childY));
            }
        }
        return nextGenPopulation;
    }

    public String mutateChild(String child) {
        StringBuilder mutatedChild = new StringBuilder(child);
        mutatedChild.setCharAt(Utility.generateRandomInteger(0,boardSize),
                        (char)(Utility.generateRandomInteger(0,boardSize)+'0'));
        return mutatedChild.toString();
    }


    public void generateBoards() {
        for (int i = 0; i < populationSize; i++) {
            populationBoards.add(new GeneratedBoard(boardSize));
            populationBoards.get(i).analyzeBoard();
        }
        sortPopulation();
    }




}

