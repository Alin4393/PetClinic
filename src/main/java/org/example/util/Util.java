package org.example.util;

import java.util.Random;

public class Util {
    public static int generateRandomBadgeId() {
        Random random = new Random();
        int badgeId =  random.nextInt(1000,10000);
        return badgeId;
    }

    public static String generateRandomCollorId() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i<4 ; i++){
            int randomInt = random.nextInt(65,90);
            result = result + Character.toString(randomInt);

        }
        return result;


    }
}
