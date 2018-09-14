package com.chesschecker.util;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

@SuppressWarnings({"PublicMethodNotExposedInInterface", "SerializableDeserializableClassInSecureContext"})
public enum PieceAbbreviation {
    KING("K"), QUEEN("Q"), ROOK("R"), BISHOP("B"), KNIGHT("N"), PAWN("P");

    private static final Map<String, PieceAbbreviation> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(PieceAbbreviation.values()),
            pieceAbbreviation -> (null != pieceAbbreviation) ? pieceAbbreviation.getAbbreviation() : null
    );
    private final String abbreviation;

    PieceAbbreviation(final String s) {
        this.abbreviation = s;
    }

    public static PieceAbbreviation fromAbbreviation(final String key) {
        final PieceAbbreviation result = PieceAbbreviation.LOOKUP.get(key);
        if (null == result) {
            throw new IllegalArgumentException("Key not found");
        } else {
            return PieceAbbreviation.LOOKUP.get(key);
        }
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

}
