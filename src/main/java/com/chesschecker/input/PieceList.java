package com.chesschecker.input;

import com.chesschecker.util.PieceAbbreviation;
import com.google.common.base.Predicates;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@SuppressWarnings({
        "ClassExtendsConcreteCollection", "serial",
        "CloneableClassInSecureContext", "CloneableClassWithoutClone"})
public final class PieceList extends HashSet<String> {
    private static final Pattern separator = Pattern.compile(", ");
    private static final char MAXROW = '8';
    private static final char MINROW = '1';

    public PieceList(Set<String> in) {
        this.addAll(in);
    }

    public PieceList(final CharSequence lineIn) {
        super();
        final String[] pieces = PieceList.separator.split(lineIn);
        this.addAll(Arrays.asList(pieces));
    }

    public static Set<String> filterOutKing(final Collection<String> moves) {
        return PieceList.filterMoves(moves,'^' + PieceAbbreviation.KING.getAbbreviation());
    }

    public static Set<String> filterMoves(final Collection<String> moves, final String regex) {
        //noinspection ChainedMethodCall
        return moves.stream()
                .filter(Predicates.containsPattern(regex).negate())
                .collect(Collectors.toSet());
    }

    public static Set<String> flipRows(final Iterable<String> pieces) {
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
