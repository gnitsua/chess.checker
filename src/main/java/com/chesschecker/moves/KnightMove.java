package com.chesschecker.moves;

import com.chesschecker.util.BitBoard;
import com.chesschecker.util.PieceAbbreviation;

import java.util.Objects;

/**
 * This class defines of a piece a Knight
 * The knight may move to one of the squares nearest to that on
 * which it stands but not on the same rank, file or diagonal. This can also be
 * considered to be a move that is not a valid Queen move and also does not move more
 * than 2 squares in any direction.
 * This is defined in 3.6 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
@SuppressWarnings("EqualsAndHashcode")
public class KnightMove extends ColoredMove {
    private static final String PIECE_ABBREVIATION = PieceAbbreviation.KNIGHT.getAbbreviation();

    public KnightMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    private boolean isValidKnightMove() {
        if (2 >= Math.abs(this.startRow - this.endRow)) {
            if (2 >= Math.abs(this.startCol - this.endCol)) {
                if (this.isValidQueenMove()) {
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

    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (this.isValidBoardMove()) {
            if (this.isValidColoredMove(friendly)) {
                if (this.isValidKnightMove()) {
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
    }

    @Override
    public final int hashCode() {
        return Objects.hash(KnightMove.PIECE_ABBREVIATION, this.startRow, this.startCol, this.endRow, this.endCol);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return KnightMove.PIECE_ABBREVIATION + this.endPositionToString();
    }
}
