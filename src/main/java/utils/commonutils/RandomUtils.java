package utils.commonutils;

import lombok.experimental.UtilityClass;
import constants.RandomUtilsConstants;

import java.util.Random;

@UtilityClass
public class RandomUtils {
    
    public static String generateRandomString() {
        byte[] array = new byte[RandomUtilsConstants.LENGTH_RANDOM_TEXT];
        new Random().nextBytes(array);
        return new String(array);
    }

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static int generateRepeatingDigit() {
        int randomDigit = getRandomInt(RandomUtilsConstants.VALUE_MIN_RANDOM, RandomUtilsConstants.VALUE_MAX_RANDOM);
        return Integer.parseInt(String.format("%d%d", randomDigit, randomDigit));
    }
}
