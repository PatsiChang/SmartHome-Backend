package com.patsi.utils;

public class GenerateIntHelper {
    public static double getMathRandom() {
        return Math.random();
    }

    public static double getGenerateRandomInt(int min, int max) {
        return Math.floor(getMathRandom() * (max - min + 1) + min);
    }
}
