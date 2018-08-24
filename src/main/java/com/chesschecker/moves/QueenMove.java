package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

/**
 * This class defines of a piece a Queen.
 * A Queen can make both Rook and Bishop moves
 * This is defined in 3.4 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class QueenMove extends SlideMove {
    protected static final String PIECE_ABBREVIATION = "Q";

    public QueenMove(int startrow, int startcol, int endrow, int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            final BishopMove bishopMove = new BishopMove(this.startRow, this.startCol, this.endRow, this.endCol);
            final RookMove rookMove = new RookMove(this.startRow, this.startCol, this.endRow, this.endCol);
            if (bishopMove.isValid(friendly, foe)) {
                return true;
            } else {
                if (rookMove.isValid(friendly, foe)) {
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
