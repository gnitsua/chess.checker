package com.chesschecker.util;

@SuppressWarnings({"PublicMethodNotExposedInInterface", "SerializableDeserializableClassInSecureContext"})
public enum PieceAbbreviation {
    KING("K"),QUEEN("Q"), ROOK("R"),BISHOP("B"),KNIGHT("N"),PAWN("P");

    private final String abbreviation;
    PieceAbbreviation(final String s){
        this.abbreviation = s;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

}
