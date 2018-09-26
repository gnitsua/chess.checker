package com.chesschecker.moves;

import com.chesschecker.util.BitBoard;
import com.chesschecker.util.PieceAbbreviation;

import java.util.Objects;

/**
 * This class defines moves for a Pawn. A pawn occupying a square
 * on the same rank as and on an adjacent file to an opponent’s pawn
 * which has just advanced two squares in one move from its original
 * square may capture this opponent’s pawn as though the latter had
 * been moved only one square. This capture is only legal
 * on the move following this advance and is called an ‘en passant’ capture.
 * This is defined in 3.7.a of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
@SuppressWarnings("EqualsAndHashcode")
public class Pawn37DMove extends ColoredMove {
    private static final String PIECE_ABBREVIATION = PieceAbbreviation.PAWN.getAbbreviation();

    public Pawn37DMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    private boolean isValidPawn37DMove(final BitBoard friendly, final BitBoard foe) {
        if(1 == this.startRow) {
            final Move movePart1 = new PawnMove(this.startRow, this.startCol, 3, this.startCol);
            final Move movePart2 = new PawnCaptureMove(3, this.startCol, this.endRow, this.endCol);
            if (movePart1.isValid(friendly, foe)) {
                if (movePart2.isValid(friendly, foe)) {
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
    @SuppressWarnings("DesignForExtension")
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (this.isValidBoardMove()) {
            if (this.isValidColoredMove(friendly)) {
                if (this.isValidPawn37DMove(friendly, foe)) {
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
        return Objects.hash("37D", Pawn37DMove.PIECE_ABBREVIATION, this.startRow, this.startCol, this.endRow, this.endCol);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return Pawn37DMove.PIECE_ABBREVIATION + this.endPositionToString();
    }
}
