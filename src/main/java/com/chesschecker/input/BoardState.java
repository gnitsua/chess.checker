package com.chesschecker.input;

import com.chesschecker.util.StringHelper;

import java.util.HashSet;
import java.util.logging.Logger;


public class BoardState {
    private static final Logger LOGGER = Logger.getLogger(BoardState.class.getName());
    private HashSet<String> white;
    private HashSet<String> black;
    private HashSet<String> move;


    public BoardState() {
        super();
        this.white = new HashSet<>(0);
        this.black = new HashSet<>(0);
        this.move = new HashSet<>(0);
    }


    public BoardState(final HashSet<String> whiteIn, final HashSet<String> blackIn, final HashSet<String> moveIn) {
        super();
        this.white = whiteIn;// making the assumption that this is Hash set of String elements that are 3 characters
        this.black = blackIn;
        this.move = moveIn;
    }

    private static HashSet<String> flip_rows(final HashSet<String> pieces) {
        final HashSet<String> result = new HashSet<>(0);
        pieces.forEach(piece -> {
            final char row = piece.charAt(2);
            final char newRow = (char) (((int)'8' - (int)row) + (int)'1');
            result.add(piece.substring(0, 2) + newRow);
        });
        return result;
    }

    public void flip_black_white() {
        this.white = BoardState.flip_rows(this.white);
        this.black = BoardState.flip_rows(this.black);
    }

    public HashSet<String> getWhite() {
        return this.white;
    }

    public HashSet<String> getBlack() {
        return this.black;
    }

    public HashSet<String> getMove() {
        return this.move;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(0);
        result.append(this.getWhite());
        result.append(StringHelper.NEW_LINE);
        result.append(this.getBlack());
        result.append(StringHelper.NEW_LINE);
        result.append(this.getMove());
        result.append(StringHelper.NEW_LINE);
        return result.toString();
    }
}
