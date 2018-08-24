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
public class PawnMove extends SlideMove {
    protected static final String PIECE_ABBREVIATION = "P";

    public PawnMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            // For a pawn, both friendly and foe prevent movement. We've already checked for friendly
            // as part of being a ColoredMoved
            if(this.isMoveToEmpty(foe)) {
                if (this.startCol == this.endCol) {
                    if (0 <= (this.endRow - this.startRow)) {
                        if (1 == this.startRow) {
                            if (3 > (this.endRow - this.startRow)){
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            if (1 >= (this.endRow - this.startRow)){
                                return true;
                            } else {
                                return false;
                            }
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
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return PIECE_ABBREVIATION + super.toString();
    }
}
