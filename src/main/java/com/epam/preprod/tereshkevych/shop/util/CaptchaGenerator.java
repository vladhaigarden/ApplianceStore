package com.epam.preprod.tereshkevych.shop.util;

import java.util.Random;

public class CaptchaGenerator {

    private static final char FIRST_SYMBOL = 'a';
    private static final char LAST_SYMBOL = 'z';

    private static final int NUMBER_OF_CHARACTERS = 26;

    private static final int INDEX_ASCII_CHAR = 48;

    private static final int MINIMUM_LENGTH_TEXT = 4;

    private static final int MAXIMUM_LENGTH_TEXT = 5;

    private static final int MAXIMUM_DIGIT = 10;

    private static final char[] symbols = getSymbols();

    public static String generateText() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getRandomLengthText(); i++) {
            result.append(getRandomElement());
        }
        return result.toString();
    }

    private static int getRandomLengthText() {
        return MINIMUM_LENGTH_TEXT + (int) (Math.random() * MAXIMUM_LENGTH_TEXT);
    }

    private static char getRandomElement() {
        boolean isElementNumber = new Random().nextBoolean();
        return isElementNumber ? getRandomNumber() : getRandomSymbol();
    }

    private static char getRandomNumber() {
        int number = INDEX_ASCII_CHAR + (int) (Math.random() * MAXIMUM_DIGIT);
        return (char) number;
    }

    private static char getRandomSymbol() {
        int index = (int) (Math.random() * NUMBER_OF_CHARACTERS);
        return symbols[index];
    }

    private static char[] getSymbols() {
        char[] symbols = new char[NUMBER_OF_CHARACTERS];
        int index = 0;
        for (char c = FIRST_SYMBOL; c <= LAST_SYMBOL; c++) {
            symbols[index++] = c;
        }
        return symbols;
    }
}