package com.chesschecker.bitboard;

public class BitBoard {

    private long state;

    public BitBoard() {
        this.state = 0L;
    }

    public long getState() {
        return this.state;
    }

    public void setOccupancy(final int row, final int col) {
        this.state |= (long)Math.pow((double)2,(double)((7 - col) + ((7 - row) * 8)));
    }

    @Override
    public String toString() {
        String result = "\n";
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                result += Long.toString((this.state >> ((j * 8) + i) & 0x01));
            }
            result += '\n';
        }
        return result;
    }
}
