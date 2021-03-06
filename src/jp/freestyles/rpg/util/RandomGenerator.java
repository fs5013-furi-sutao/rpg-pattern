package jp.freestyles.rpg.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static int generateRandomInt(int num) {
        return ThreadLocalRandom.current().nextInt(num);
    }

    public static int generateRandomIntInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean isInRate(double rate) {
        return ThreadLocalRandom.current().nextDouble() < rate;
    }
}
