package util;

import java.util.Random;

public class Dices {
    final static private Random random = new Random();

    public static int throwBy(int amountDices, int diceFaces) {
        int value = 0;

        for (int i = 0; i < amountDices; i++) {
           value += random.nextInt(diceFaces) + 1;
        }

        return value;
    }
}
