package com.chesschecker.util;

public final class Column {
    private Column() {
    }

    public static String columnNumberToLetter(final int col) {
        //Apparently negative numbers don't break this. So no need for input checking
        return Character.toString((char) ((int) 'a' + col));
    }
}
