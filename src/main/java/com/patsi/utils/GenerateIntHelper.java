package com.patsi.utils;

public class GenerateIntHelper {
    public static double mathRandom() {
        return Math.random();
    }

    public static double generateRandomInt(int min, int max) {
        return Math.floor(mathRandom() * (max - min + 1) + min);
    }
}
