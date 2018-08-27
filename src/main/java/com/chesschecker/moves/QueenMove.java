package com.chesschecker.moves;

import com.chesschecker.util.BitBoard;
import com.chesschecker.util.PieceAbbreviation;

/**
 * This class defines of a piece a Queen.
 * A Queen can make both Rook and Bishop moves
 * This is defined in 3.4 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class QueenMove extends SlideMove {
    private static final String PIECE_ABBREVIATION = PieceAbbreviation.QUEEN.getAbbreviation();

    public QueenMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (this.isValidBoardMove()) {
            if (this.isValidColoredMove(friendly)) {
                if (this.isValidSlideMove(friendly, foe)) {
                    if (this.isValidQueenMove()) {
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
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return QueenMove.PIECE_ABBREVIATION + this.endPositionToString();
    }
}
