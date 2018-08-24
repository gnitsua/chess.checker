package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

/**
 * This class defines moves for a piece of a certain color.
 * Moves are not valid if they end on a piece of the same color,
 * or if the piece it lands on is itself (it doesn't actually move)
 * This is defined in 3.1 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class ColoredMove extends BoardMove {
    public ColoredMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    protected boolean isMoveToEmpty(BitBoard friendly) {
        BitBoard temp = new BitBoard();
        temp.setOccupancy(this.endRow, this.endCol);
        if (BitBoard.and(temp, friendly).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            if (this.isSelfMove()) {
                return true;
            } else {
                if (this.isMoveToEmpty(friendly)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }

    }
}
