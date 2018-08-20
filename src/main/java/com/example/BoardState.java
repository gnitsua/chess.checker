package com.example;

import java.util.HashSet;
import java.util.logging.Logger;


public class BoardState {
    private static final Logger LOGGER = Logger.getLogger(BoardState.class.getName());
    private HashSet<String> white;
    private HashSet<String> black;
    private HashSet<String> move;


    BoardState() {
        super();
        this.white = new HashSet<>(0);
        this.black = new HashSet<>(0);
        this.move = new HashSet<>(0);
    }


    BoardState(HashSet<String> white_in, HashSet<String> black_in, HashSet<String> move_in) {
        super();
        this.white = white_in;// making the assumption that this is Hash set of String elements that are 3 characters
        this.black = black_in;
        this.move = move_in;
    }

    private HashSet<String> flip_rows(HashSet<String> pieces) {
        HashSet<String> result = new HashSet<>();
        for (String piece : pieces) {
            char row = piece.charAt(2);
            char new_row = (char) ('8' - row + '1');
            result.add(piece.substring(0, 2) + new_row);
        }
        return result;
    }

    public void flip_black_white() {
        this.white = this.flip_rows(this.white);
        this.black = this.flip_rows(this.black);
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
        return this.getWhite().toString() + '\n' + this.getBlack().toString() + "\n" + this.getMove().toString();
    }
}
