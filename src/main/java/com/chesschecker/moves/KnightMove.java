package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

/**
 * This class defines of a piece a Knight
 * The knight may move to one of the squares nearest to that on
 * which it stands but not on the same rank, file or diagonal. This can also be
 * considered to be a move that is not a valid Queen move and also does not move more
 * than 2 squares in any direction.
 * This is defined in 3.6 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class KnightMove extends ColoredMove {
    protected static final String PIECE_ABBREVIATION = "N";

    public KnightMove(int startrow, int startcol, int endrow, int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            if (2 >= Math.abs(this.startRow - this.endRow)) {
                if (2 >= Math.abs(this.startCol - this.endCol)) {
                    final QueenMove queenMove = new QueenMove(this.startRow, this.startCol, this.endRow, this.endCol);
                    if (queenMove.isValid(friendly, foe)) {
                        if (this.isSelfMove()) {
                            return true;

                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
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
