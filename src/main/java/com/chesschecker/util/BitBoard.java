package com.chesschecker.util;

public interface BitBoard {


    void setOccupancy(final int row, final int col);

    long toLong();



    @Override
    String toString();

    boolean isEmpty();

    @Override
    boolean equals(final Object obj);
}
