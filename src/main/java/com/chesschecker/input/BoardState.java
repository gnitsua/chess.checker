package com.chesschecker.input;

import com.chesschecker.util.StringHelper;
import com.chesschecker.util.PieceAbbreviation;
import com.google.common.base.Predicates;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@SuppressWarnings("PublicMethodNotExposedInInterface")
public class BoardState {
    private static final char MAXROW = '8';
    private static final char MINROW = '1';
    @SuppressWarnings("FieldNotUsedInToString")
    private final Set<String> white;
    @SuppressWarnings("FieldNotUsedInToString")
    private final Set<String> black;
    private final Set<String> move;
    @SuppressWarnings("FieldNotUsedInToString")
    private final boolean mirrored;


    public BoardState() {
        super();
        this.white = new HashSet<>(0);
        this.black = new HashSet<>(0);
        this.move = new HashSet<>(0);
        this.mirrored = false;
    }


    public BoardState(final Set<String> whiteIn, final Set<String> blackIn, final Set<String> moveIn) {
        super();
        if (whiteIn.containsAll(moveIn)) {
            this.white = whiteIn;// making the assumption that this is Hash set of String elements that are 3 characters
            this.black = blackIn;
            this.move = moveIn;
            this.mirrored = false;
        } else {
            if (blackIn.containsAll(moveIn)) {
                this.white = BoardState.flip_rows(blackIn);
                this.black = BoardState.flip_rows(whiteIn);
                this.move = moveIn;
            } else {// The piece that is supposed to be moved is not actually a peice. Should return no moves
                this.white = new HashSet<>(0);
                this.black = new HashSet<>(0);
                this.move = new HashSet<>(0);
            }
            this.mirrored = true;
        }
    }

    // I need to test this function somehow
    private static Set<String> flip_rows(final Iterable<String> pieces) {
        final Set<String> result = new HashSet<>(0);
        for (final String piece : pieces) {
            final char row = piece.charAt(2);
            //noinspection NumericCastThatLosesPrecision
            final char newRow = (char) (((int) BoardState.MAXROW - (int) row) + (int) BoardState.MINROW);
            final String pieceTypeAndCol = piece.substring(0, 2);
            result.add(pieceTypeAndCol + newRow);
        }
        return result;
    }

    public final Set<String> getWhite() {
        if (this.mirrored) {
            return BoardState.flip_rows(this.black);
        } else {
            return this.white;
        }
    }
    private static Set<String> filterOutKing(final Collection<String> moves){
        //noinspection NestedMethodCall,ChainedMethodCall
        return moves.stream().filter(Predicates.containsPattern("^K").negate()).collect(Collectors.toSet());
    }

    public final Set<String> getWhiteWhithoutKing() {
        //noinspection NestedMethodCall,ChainedMethodCall
        return BoardState.filterOutKing(this.getWhite());
    }



    public final Set<String> getBlack() {
        if (this.mirrored) {
            return BoardState.flip_rows(this.white);
        } else {
            return this.black;
        }
    }

    public final Set<String> getBlackWhithoutKing() {
        //noinspection NestedMethodCall
        return BoardState.filterOutKing(this.getBlack());
    }

    public final Set<String> getMove() {
        return this.move;
    }

    @Override
    @SuppressWarnings("NestedMethodCall")
    public final String toString() {
        return String.valueOf(this.getWhite()) +
                StringHelper.NEW_LINE +
                this.getBlack() +
                StringHelper.NEW_LINE +
                this.move +
                StringHelper.NEW_LINE;
    }
}
