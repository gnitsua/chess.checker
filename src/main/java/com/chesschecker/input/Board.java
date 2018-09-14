package com.chesschecker.input;

import com.chesschecker.util.BitBoard;
import com.chesschecker.util.StringHelper;

import java.math.BigDecimal;


@SuppressWarnings("EqualsAndHashcode")
public final class Board implements BitBoard {

    private long state;

    public Board() {
        super();
        this.state = 0L;
    }

    public Board(final long statein) {
        super();
        this.state = statein;
    }

    public static BitBoard or(final BitBoard x, final BitBoard y) {
        final long longX = x.toLong();
        final long longY = y.toLong();
        return new Board(longX | longY);
    }

    public static BitBoard and(final BitBoard x, final BitBoard y) {
        final long longX = x.toLong();
        final long longY = y.toLong();
        return new Board(longX & longY);
    }

    public static BitBoard xor(final BitBoard x, final BitBoard y) {
        final long longX = x.toLong();
        final long longY = y.toLong();
        return new Board(longX ^ longY);
    }

    public static BitBoard not(final BitBoard x) {
        final long longX = x.toLong();
        return new Board(~longX);
    }

    public long toLong() {
        return this.state;
    }

    /**
     * Note: if the position is out of bounds, this function leaves occupancy unchanged
     *
     * @param row
     * @param col
     */
    public void setOccupancy(final int row, final int col) {
        if (0 <= row) {
            if (8 > row) {
                if (0 <= col) {
                    if (8 > col) {
                        final BigDecimal bigTwo = new BigDecimal("2");
                        final BigDecimal result = bigTwo.pow(((7 - col) + ((7 - row) * 8)));
                        this.state |= result.longValue();
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
        this.state = ((this.state >>> oneRow) & k1) | ((this.state & k1) << oneRow);
        this.state = ((this.state >>> twoRow) & k2) | ((this.state & k2) << twoRow);
        this.state = (this.state >>> threeRow) | (this.state << threeRow);
    }

    public boolean isEmpty() {
        return 0L == this.toLong();
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

    @Override
    public boolean equals(final Object obj) {
        boolean returnVal = false;
        if (obj == this) {
            returnVal = true;
        } else if (obj instanceof BitBoard) {

            // typecast o to Complex so that we can compare data members
            final BitBoard otherBoard = (BitBoard) obj;

            // Compare the data members and return accordingly
            returnVal = this.toLong() == otherBoard.toLong();
        }

        return returnVal;
    }
}
