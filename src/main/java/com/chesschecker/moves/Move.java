package com.chesschecker.moves;

import com.chesschecker.util.BitBoard;

public interface Move extends Comparable<Move>{
    boolean isValid(final BitBoard friendly, final BitBoard foe);

    int getStartRow();

    int getStartCol();

    int getEndRow();

    int getEndCol();

    BitBoard getOccupancy();

    BitBoard getAttacking();

    String toString();

}
