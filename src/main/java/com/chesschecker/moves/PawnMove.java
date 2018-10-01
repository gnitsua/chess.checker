package com.chesschecker.moves;

import com.chesschecker.util.BitBoard;
import com.chesschecker.util.PieceAbbreviation;

import java.util.Objects;

/**
 * This class defines moves for a Pawn (not including special moves.
 * The pawn may move forward to the square immediately in front of it
 * on the same file, provided that this square is unoccupied.
 * The pawn may move to a square occupied by an opponent’s piece diagonally
 * in front of it on an adjacent file, capturing that piece.
 * This is defined in 3.7.a of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
@SuppressWarnings("EqualsAndHashcode")
public class PawnMove extends SlideMove {
    private static final String PIECE_ABBREVIATION = PieceAbbreviation.PAWN.getAbbreviation();

    public PawnMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    private boolean isValidPawnMove(final BitBoard foe) {
        // For a pawn, both friendly and foe prevent movement. We've already checked for friendly
        // as part of being a ColoredMoved
        if (this.isMoveToEmpty(foe)) {
            if (this.startCol == this.endCol) {
                if (0 <= (this.endRow - this.startRow)) {
                    if (1 == this.startRow) {
                        if (3 > (this.endRow - this.startRow)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        if (1 >= (this.endRow - this.startRow)) {
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

    @Override
    @SuppressWarnings("DesignForExtension")
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (this.isValidBoardMove()) {
            if (this.isValidColoredMove(friendly)) {
                if (this.isValidSlideMove(friendly, foe)) {
                    if (this.isValidPawnMove(foe)) {
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
        return Objects.hash(PawnMove.PIECE_ABBREVIATION, this.startRow, this.startCol, this.endRow, this.endCol);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return PawnMove.PIECE_ABBREVIATION + this.endPositionToString();
    }
}
