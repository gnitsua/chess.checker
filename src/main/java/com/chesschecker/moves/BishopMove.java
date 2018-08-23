package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;


/**
 * This class defines of a piece a Bishop.
 * The bishop may move to any square along a diagonal on which it stands.
 * This is defined in 3.2 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class BishopMove extends SlideMove {
    protected static final String PIECE_ABBREVIATION = "B";

    public BishopMove(int startrow, int startcol, int endrow, int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            if (Math.abs(this.startRow - this.endRow) == Math.abs(this.startCol - this.endCol)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.PIECE_ABBREVIATION + super.toString();
    }
}
