package com.chesschecker.moves;

import com.chesschecker.input.Board;
import com.chesschecker.util.BitBoard;
import com.chesschecker.util.PieceAbbreviation;

import java.util.Objects;

/**
 * This class defines moves for a Pawn.
 * The pawn may move to a square occupied by an opponent’s piece diagonally
 * in front of it on an adjacent file, capturing that piece.
 * This is defined in 3.7.c of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class PawnCaptureMove extends SlideMove {
    private static final String PIECE_ABBREVIATION = PieceAbbreviation.PAWN.getAbbreviation();

    public PawnCaptureMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    private boolean isValidPawnCaptureMove(final BitBoard foe) {
        if (1 == (this.endRow - this.startRow)) {
            if (1 == Math.abs(this.startCol - this.endCol)) {
                final BitBoard postMoveBitboard = this.getAttacking();
                final BitBoard captures = Board.and(postMoveBitboard, foe);
                if (captures.isEmpty()) {
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
                if (this.isValidSlideMove(friendly, foe)) {
                    if (this.isValidPawnCaptureMove(foe)) {
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
    public final int hashCode() {
        return Objects.hash(PawnCaptureMove.PIECE_ABBREVIATION, this.startRow, this.startCol, this.endRow, this.endCol);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return PawnCaptureMove.PIECE_ABBREVIATION + this.endPositionToString();
    }
}
