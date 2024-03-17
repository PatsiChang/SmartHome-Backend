package com.patsi.utils;

import java.io.IOException;

public class GenerateIntHelper {
    public static double generateRandomInt(int min, int max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }
}
