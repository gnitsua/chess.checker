package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

/**
 * This class defines the king's half of the castling move.
 * The king is transferred from its original square two squares towards the rook on its original square
 * This is defined in 3.8.b of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class CastlingKingMove extends RookMove {
    private static final String PIECE_ABBREVIATION = "K";


    public CastlingKingMove(int startrow, int startcol, int endrow, int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            if (0 == this.startRow) {
                if (4 == this.startCol) {
                    if (2 == this.endCol) {
                        return true;
                    } else {
                        if (6 == this.endCol) {
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
    }

    public String toString() {
        return CastlingKingMove.PIECE_ABBREVIATION + super.toString().substring(1);
    }
}
