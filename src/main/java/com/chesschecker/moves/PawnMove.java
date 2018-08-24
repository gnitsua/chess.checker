package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

/**
 * This class defines moves for a Pawn (not including special moves.
 * MThe pawn may move forward to the square immediately in front of it
 * on the same file, provided that this square is unoccupied
 * This is defined in 3.7.a of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class PawnMove extends ColoredMove {
    protected static final String PIECE_ABBREVIATION = "P";

    public PawnMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            if (1 == (this.endRow - this.startRow)) {
                if (this.startCol == this.endCol) {
                    return true;
                } else {
                    if (1 == Math.abs(this.startCol - this.endCol)) {
                        if (BitBoard.and(this.getPostmoveBitboard(), foe).isEmpty()) {
                            return false;// there wasn't a foe in the space so it wasn't a valid take
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
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
