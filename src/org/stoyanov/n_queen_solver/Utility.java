package org.stoyanov.n_queen_solver;

import java.util.Random;

public class Utility {
    public static int generateRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public static double generateRandomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }
}
