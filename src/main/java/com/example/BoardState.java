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
        this.white = white_in;
        this.black = black_in;
        this.move = move_in;
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
