package com.chesschecker.util;

public final class Column {
    private Column() {
    }

    public static String columnNumberToLetter(final int col) {
        //Apparently negative numbers don't break this. So no need for input checking
        return Character.toString((char) ((int) 'a' + col));
    }

    public static int columnLetterToNumber(final String col) {
        //Apparently negative numbers don't break this. So no need for input checking
        final char columnLetter = col.charAt(0);
        return (int)columnLetter-(int)'a';
    }
}
