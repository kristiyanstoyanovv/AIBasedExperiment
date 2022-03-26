package com.Kris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Problem {

    private List<Board> populationBoards = new ArrayList<>();
    private final static int POPULATION_SIZE = 150;

    public void solveProblem() {

        while (true) {
            generateBoards(POPULATION_SIZE);
            sortPopulation();

            while(true) {
                for (Board board : populationBoards) {
                    if (board.getSumOfAllHittablePositions() == 0) {
                        solutionFound();
                        return;
                    }
                }
                populationBoards = crossOverFunction();
                sortPopulation();
                System.out.println("Population size: " + populationBoards.size());
            }
        }
    }

    public void solutionFound() {
        populationBoards.get(0).displayBoard();
        System.out.println(populationBoards.get(0).getSumOfAllHittablePositions());
        System.out.println(populationBoards.get(0).queensPosAsString);
        System.out.println();
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
        while (true) {
            for (Board board : populationBoards) {
                if (Utility.generateRandomDouble() > (double) board.getSumOfAllHittablePositions() / 56) {
                    return board;
                }
            }
        }
    }

    public ArrayList<Board> crossOverFunction() {
        ArrayList<Board> nextGenPopulation = new ArrayList();
        for(int i = 0; i < populationBoards.size(); i+=2) {
            int randomNumber = Utility.generateRandomInteger(0, 8);
            String parentXS = fitnessFunction().getQueensPosAsString();
            String parentYS = fitnessFunction().getQueensPosAsString();
            String childX = parentXS.substring(0, randomNumber) +
                    parentYS.substring(randomNumber);
            String childY = parentYS.substring(0, randomNumber) +
                    parentXS.substring(randomNumber);
                nextGenPopulation.add(new StringBoard(mutateChild(childX)));
                nextGenPopulation.add(new StringBoard(mutateChild(childY)));
        }
        return nextGenPopulation;
    }

    public String mutateChild(String child) {
        StringBuilder mutatedChild = new StringBuilder(child);
        mutatedChild.setCharAt(Utility.generateRandomInteger(0,8),(char)(Utility.generateRandomInteger(0,8)+'0'));
        return mutatedChild.toString();
    }


    public void generateBoards(int boardsCount) {
        for (int i = 0; i < boardsCount; i++) {
            populationBoards.add(new GeneratedBoard());
            populationBoards.get(i).analyzeBoard();
        }
    }




}

