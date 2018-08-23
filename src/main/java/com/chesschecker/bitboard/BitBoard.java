package com.chesschecker.bitboard;

import com.chesschecker.util.StringHelper;

import java.math.BigDecimal;

public final class BitBoard {

    private long state;

    public BitBoard() {
        this.state = 0L;
    }

    public BitBoard(long state) {
        this.state = state;
    }

    public static BitBoard or(BitBoard a, BitBoard b) {
        return new BitBoard(a.getState() | b.getState());
    }

    public static BitBoard and(BitBoard a, BitBoard b) {
        return new BitBoard(a.getState() & b.getState());
    }

    public static BitBoard xor(BitBoard a, BitBoard b) {
        return new BitBoard(a.getState() ^ b.getState());
    }

    public static BitBoard not(BitBoard a) {
        return new BitBoard(~a.getState());
    }

    public long getState() {
        return this.state;
    }

    public void setOccupancy(final int row, final int col) {
        if (0 <= row) {
            if (8 >= row) {
                if (0 <= col) {
                    if (8 >= col) {
                        BigDecimal two = new BigDecimal("2");
                        this.state |= two.pow(((7 - col) + ((7 - row) * 8))).longValue();
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

    public void mirrorVertical() {
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
    public String toString() {
        final long oneRow = 8L;
        final StringBuilder result = new StringBuilder(0);
        result.append(StringHelper.NEW_LINE);
        for (int j = 0; 8 > j; j++) {
            for (int i = 7; 0 <= i; i--) {
                result.append((this.state >> (((long) j * oneRow) + (long) i)) & 0x01L);
            }
            result.append(StringHelper.NEW_LINE);
        }
        return result.toString();
    }

    public boolean isEmpty() {
        return 0L == this.state;
    }

    @Override
    public boolean equals(final Object obj) {
        boolean returnVal = false;
        if (obj == this) {
            returnVal = true;
        } else if (obj instanceof BitBoard) {

            // typecast o to Complex so that we can compare data members
            final BitBoard otherBoard = (BitBoard) obj;

            // Compare the data members and return accordingly
            returnVal = this.getState() == otherBoard.getState();
        }

        return returnVal;
    }
}
