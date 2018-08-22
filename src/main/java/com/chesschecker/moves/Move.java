package com.chesschecker.moves;

import com.chesschecker.util.Column;

public abstract class Move {
    protected static String PIECE_ABBREVIATION = "";

    protected Move() {
        super();
    }

    public boolean isValid() {
        return false;
    }


}
