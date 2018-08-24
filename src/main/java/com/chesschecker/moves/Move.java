package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

public interface Move {
    boolean isValid(final BitBoard friendly, final BitBoard foe);

    String toString();
}
