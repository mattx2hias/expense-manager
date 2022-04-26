package dev.matthias.utilities;

import java.util.Random;

public class GenerateID {

    private static final Random rand = new Random();

    private GenerateID(){}

    public static int generateRandomID() {
        return rand.nextInt(9999-1000) + 1000;
    }
}
