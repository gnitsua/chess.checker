package com.chesschecker.util;

public interface BitBoard {


    void setOccupancy(final int row, final int col);

    long toLong();

    void mirrorVertical();

    @Override
    String toString();

    static boolean isEmpty(final BitBoard bitBoard){
        return 0L == bitBoard.toLong();
    }

    @Override
    boolean equals(final Object obj);
}
