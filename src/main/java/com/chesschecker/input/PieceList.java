package com.chesschecker.input;

import com.chesschecker.util.PieceAbbreviation;
import com.google.common.base.Predicates;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class PieceList extends HashSet<String> {
    private static final Pattern separator = Pattern.compile(", ");
    private static final char MAXROW = '8';
    private static final char MINROW = '1';

    public PieceList() {
        super();
    }

    public PieceList(final CharSequence lineIn) {
        super();
        final String[] pieces = PieceList.separator.split(lineIn);
        this.addAll(Arrays.asList(pieces));
    }

    static Set<String> filterOutKing(final Collection<String> moves) {
        //noinspection ChainedMethodCall
        return moves.stream()
                .filter(Predicates.containsPattern('^' + PieceAbbreviation.KING.getAbbreviation()).negate())
                .collect(Collectors.toSet());
    }

    // I need to test this function somehow
    static Set<String> flip_rows(final Iterable<String> pieces) {
        final Set<String> result = new HashSet<>(0);
        for (final String piece : pieces) {
            final char row = piece.charAt(2);
            //noinspection NumericCastThatLosesPrecision
            final char newRow = (char) (((int) PieceList.MAXROW - (int) row) + (int) PieceList.MINROW);
            final String pieceTypeAndCol = piece.substring(0, 2);
            result.add(pieceTypeAndCol + newRow);
        }
        return result;
    }
}
