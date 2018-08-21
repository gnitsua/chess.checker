package com.chesschecker.bitboard;

import com.chesschecker.util.StringHelper;

public class BitBoard {

    private long state;

    public BitBoard() {
        this.state = 0L;
    }

    public long getState() {
        return this.state;
    }

    public void setOccupancy(final int row, final int col) {
        if (0 <= row) {
            if (8 >= row) {
                if (0 <= col) {
                    if (8 >= col) {
                        this.state |= (long) Math.pow((double) 2, (double) ((7 - col) + ((7 - row) * 8)));
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }

    }

    public final void mirrorVertical() {
        final long k1 = 0x00FF00FF00FF00FFL;
        final long k2 = 0x0000FFFF0000FFFFL;
        final long oneRow = 8L;
        final long twoRow = 16L;
        final long threeRow = 32L;
        this.state = ((this.state >> oneRow) & k1) | ((this.state & k1) << oneRow);
        this.state = ((this.state >> twoRow) & k2) | ((this.state & k2) << twoRow);
        this.state = (this.state >> threeRow) | (this.state << threeRow);
    }

    @Override
    public final String toString() {
        final long oneRow = 8L;
        final StringBuilder result = new StringBuilder(0);
        result.append(StringHelper.NEW_LINE);
        for (int j = 0; 8 > j; j++) {
            for (int i = 0; 8 > i; i++) {
                result.append((this.state >> (((long)j * oneRow) + (long)i)) & 0x01L);
            }
            result.append(StringHelper.NEW_LINE);
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof BitBoard)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        final BitBoard c = (BitBoard) o;

        // Compare the data members and return accordingly
        return this.state == c.getState();
    }
}
