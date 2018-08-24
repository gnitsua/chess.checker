package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

/**
 * This class defines moves for a Pawn.
 * In its first move the pawn may advance two squares along the same
 * file, provided that both squares are unoccupied
 * The pawn may move to a square occupied by an opponent’s piece diagonally
 * in front of it on an adjacent file, capturing that piece.
 * This is defined in 3.7.a of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class PawnCaptureMove extends ColoredMove {
    protected static final String PIECE_ABBREVIATION = "P";

    public PawnCaptureMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            if (1 == (this.endRow - this.startRow)) {
                if (1 == Math.abs(this.startCol - this.endCol)) {
                    if (BitBoard.and(this.getPostmoveBitboard(), foe).isEmpty()) {
                        return false;
                    } else {
                        return true;
                    }

                } else {
                    return false;
                }
            } else {
                if (this.isSelfMove()) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return PIECE_ABBREVIATION + super.toString();
    }
}
