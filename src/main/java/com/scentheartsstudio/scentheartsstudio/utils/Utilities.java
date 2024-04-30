package com.scentheartsstudio.scentheartsstudio.utils;

import java.util.Random;

public class Utilities {

    public static String generateToken6Digit() {
        String token = "";
        for (int i = 0; i < 6; i++) {
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(10);
            token += randomNumber;
        }
        return token;
    }

}
