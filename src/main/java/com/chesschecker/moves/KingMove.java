package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;

/**
 * This class defines of a piece a King
 * The king may move by moving to an adjoining square
 * This is defined in 3.8.a of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class KingMove extends QueenMove {
    protected static final String PIECE_ABBREVIATION = "K";

    public KingMove(int startrow, int startcol, int endrow, int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public final boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            if (1 >= Math.abs(this.startRow - this.endRow)){
                if(1 >= Math.abs(this.startCol - this.endCol)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return KingMove.PIECE_ABBREVIATION + super.toString().substring(1);//TODO: this is terrible
    }
}
