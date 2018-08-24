package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

/**
 * This class defines moves for a Pawn (not including special moves.
 * The pawn may move forward to the square immediately in front of it
 * on the same file, provided that this square is unoccupied.
 * The pawn may move to a square occupied by an opponent’s piece diagonally
 * in front of it on an adjacent file, capturing that piece.
 * This is defined in 3.7.a of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class Pawn37DMove extends BoardMove {
    protected static final String PIECE_ABBREVIATION = "P";

    public Pawn37DMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            final PawnMove movePart1 = new PawnMove(this.startRow, this.startCol, 3, this.startCol);
            final PawnCaptureMove movePart2 = new PawnCaptureMove(3, this.startCol, this.endRow, this.endCol);
            if (this.isSelfMove()) {
                return true;
            } else {
                if (movePart1.isValid(friendly, foe)) {
                    if (movePart2.isValid(friendly, foe)) {
                        return true;
                    } else {
                        return false;
                    }
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
