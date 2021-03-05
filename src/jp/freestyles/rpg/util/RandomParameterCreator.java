package jp.freestyles.rpg.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomParameterCreator {

    public static int generateRandomValue(int min, int max) {

        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
