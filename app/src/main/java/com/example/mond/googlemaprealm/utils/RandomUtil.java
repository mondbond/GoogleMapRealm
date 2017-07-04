package com.example.mond.googlemaprealm.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    private final static int ICON_TYPE_COUNT = 4;

    private static Random mRandom;

    public static String generateUUID() {

        return UUID.randomUUID().toString();
    }

    public static int generateIconType() {
        if(mRandom == null) {
            mRandom = new Random();
        }

        return mRandom.nextInt(ICON_TYPE_COUNT);
    }
}
