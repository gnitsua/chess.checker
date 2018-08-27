package com.chesschecker.moves;

import com.chesschecker.util.BitBoard;

/**
 * This class defines of a piece a King
 * The king may move by moving to an adjoining square
 * This is defined in 3.8.a of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class KingMove extends QueenMove {
    private static final String PIECE_ABBREVIATION = "K";

    public KingMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    private boolean isValidKingMove() {
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

    }

    @Override
    public final boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if(this.isValidBoardMove()) {
            if(this.isValidColoredMove(friendly)) {
                if(this.isValidSlideMove(friendly, foe)) {
                    if(this.isValidQueenMove()) {
                        if(this.isValidKingMove()) {
                            return true;
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
        } else {
            return false;
        }
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return KingMove.PIECE_ABBREVIATION + this.endPositionToString();
    }
}
