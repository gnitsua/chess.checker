package com.chesschecker.moves;

import com.chesschecker.util.BitBoard;
import com.chesschecker.util.PieceAbbreviation;

import java.util.Objects;


/**
 * This class defines of a piece a Bishop.
 * The bishop may move to any square along a diagonal on which it stands.
 * This is defined in 3.2 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
@SuppressWarnings("EqualsAndHashcode")
public class BishopMove extends QueenMove {
    private static final String PIECE_ABBREVIATION = PieceAbbreviation.BISHOP.getAbbreviation();

    public BishopMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (this.isValidBoardMove()) {
            if (this.isValidColoredMove(friendly)) {
                if (this.isValidSlideMove(friendly, foe)) {
                    if (this.isValidQueenMove()) {
                        if (this.isValidRookMove()) {
                            return false;
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
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return Objects.hash(BishopMove.PIECE_ABBREVIATION, this.startRow, this.startCol, this.endRow, this.endCol);
    }


    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return BishopMove.PIECE_ABBREVIATION + this.endPositionToString();
    }
}
