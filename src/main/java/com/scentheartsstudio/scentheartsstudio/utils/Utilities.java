package com.scentheartsstudio.scentheartsstudio.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static String generateOrderNumber(Long userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateOrderNumber =sdf.format(new Date());
	    return "ORD-" + userId + "-" + dateOrderNumber;
    }
}
